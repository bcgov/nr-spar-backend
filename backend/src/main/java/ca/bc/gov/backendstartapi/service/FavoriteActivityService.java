package ca.bc.gov.backendstartapi.service;

import ca.bc.gov.backendstartapi.dto.FavoriteActivityDto;
import ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity;
import ca.bc.gov.backendstartapi.entity.UserEntity;
import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import ca.bc.gov.backendstartapi.exception.FavoriteActivityExistsToUser;
import ca.bc.gov.backendstartapi.exception.ActivityNotFoundException;
import ca.bc.gov.backendstartapi.exception.UserNotFoundException;
import ca.bc.gov.backendstartapi.repository.FavoriteActivityRepository;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** This class contains all routines and database access to a users' favorite activity. */
@Setter
@Service
@NoArgsConstructor
public class FavoriteActivityService {

  private FavoriteActivityRepository favoriteActivityRepository;

  private UserService userService;

  /**
   * Create a FavoriteActivityService instance.
   *
   * @param userService a userService instance
   * @param favoriteActivityRepository a favoriteActivityRepository instance
   */
  @Autowired
  public FavoriteActivityService(
      UserService userService,
      FavoriteActivityRepository favoriteActivityRepository) {
    this.userService = userService;
    this.favoriteActivityRepository = favoriteActivityRepository;
  }

  /**
   * Create a user's activity in the database.
   *
   * @param activityDto a dto containing the activity title
   * @return the FavoriteActivityEntity created
   */
  public FavoriteActivityEntity createUserActivity(FavoriteActivityDto activityDto) {
    UserEntity user = userService.getLoggerUserEntity();

    Optional<ActivityEnum> activityEnum = ActivityEnum.getByTitle(activityDto.title());
    if (activityEnum.isEmpty()) {
      throw new ActivityNotFoundException();
    }

    List<FavoriteActivityEntity> userFavList = favoriteActivityRepository.findAllByUser(user);
    if (userFavList.stream().anyMatch(ac -> ac.getActivityTitle().equals(activityDto.title()))) {
      throw new FavoriteActivityExistsToUser();
    }

    FavoriteActivityEntity activityEntity = new FavoriteActivityEntity();
    activityEntity.setUser(user);
    activityEntity.setActivityTitle(activityEnum.get().getTitle());
    return favoriteActivityRepository.save(activityEntity);
  }

  public List<FavoriteActivityEntity> getAllUserFavoriteActivities() {
    UserEntity user = userService.getLoggerUserEntity();
    return favoriteActivityRepository.findAllEnabledByUser(Boolean.TRUE, user);
  }
}
