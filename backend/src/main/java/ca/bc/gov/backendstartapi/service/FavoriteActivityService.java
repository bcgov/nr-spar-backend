package ca.bc.gov.backendstartapi.service;

import ca.bc.gov.backendstartapi.dto.FavoriteActivityCreateDto;
import ca.bc.gov.backendstartapi.dto.FavoriteActivityUpdateDto;
import ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity;
import ca.bc.gov.backendstartapi.entity.UserEntity;
import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import ca.bc.gov.backendstartapi.exception.ActivityNotFoundException;
import ca.bc.gov.backendstartapi.exception.FavoriteActivityExistsToUser;
import ca.bc.gov.backendstartapi.repository.FavoriteActivityRepository;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** This class contains all routines and database access to a users' favorite activity. */
@Slf4j
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
      UserService userService, FavoriteActivityRepository favoriteActivityRepository) {
    this.userService = userService;
    this.favoriteActivityRepository = favoriteActivityRepository;
  }

  /**
   * Create a user's activity in the database.
   *
   * @param activityDto a dto containing the activity title
   * @return the FavoriteActivityEntity created
   */
  public FavoriteActivityEntity createUserActivity(FavoriteActivityCreateDto activityDto) {
    UserEntity user = userService.getLoggerUserEntity();

    log.info("Creating activity {} to user {}", activityDto.title(), user.getId());

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

  /**
   * Retrieve all favorite activities to a specific user.
   *
   * @return a list of FavoriteActivityEntity or an empty list
   */
  public List<FavoriteActivityEntity> getAllUserFavoriteActivities() {
    UserEntity user = userService.getLoggerUserEntity();
    return favoriteActivityRepository.findAllByEnabledAndUser(Boolean.TRUE, user.getId());
  }

  /**
   * Updates a user activity.
   *
   * @param id the id of the activity to be updated.
   * @param updateDto a FavoriteActivityUpdateDto containing the values to be updated
   * @return a FavoriteActivityEntity updated
   * @throws ActivityNotFoundException
   */
  public FavoriteActivityEntity updateUserActivity(Long id, FavoriteActivityUpdateDto updateDto) {
    UserEntity user = userService.getLoggerUserEntity();

    log.info("Updating activity {} to user {}", id, user.getId());
    Optional<FavoriteActivityEntity> activityEntity = favoriteActivityRepository.findById(id);
    if (activityEntity.isEmpty()) {
      throw new ActivityNotFoundException();
    }

    FavoriteActivityEntity entity =
        activityEntity
            .get()
            .withHighlighted(updateDto.highlighted())
            .withEnabled(updateDto.enabled());

    return favoriteActivityRepository.save(entity);
  }

  /**
   * Deletes a user activity by the activity id number.
   *
   * @param id the id of the activity
   */
  public void deleteUserActivity(Long id) {
    UserEntity user = userService.getLoggerUserEntity();

    log.info("Deleting activity {} to user {}", id, user.getId());
    Optional<FavoriteActivityEntity> activityEntity = favoriteActivityRepository.findById(id);
    if (activityEntity.isEmpty()) {
      throw new ActivityNotFoundException();
    }

    favoriteActivityRepository.deleteById(id);
  }
}
