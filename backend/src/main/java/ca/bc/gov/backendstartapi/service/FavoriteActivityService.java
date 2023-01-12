package ca.bc.gov.backendstartapi.service;

import ca.bc.gov.backendstartapi.dto.FavoriteActivityDto;
import ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity;
import ca.bc.gov.backendstartapi.entity.UserEntity;
import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import ca.bc.gov.backendstartapi.repository.FavoriteActivityRepository;
import ca.bc.gov.backendstartapi.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class contains all routines and database access to a users' favorite activity.
 */
@Setter
@Service
@NoArgsConstructor
public class FavoriteActivityService {

  private UserRepository userRepository;

  private FavoriteActivityRepository favoriteActivityRepository;

  /**
   * Create a FavoriteActivityService instance.
   *
   * @param userRepository a userRepository instance
   */
  @Autowired
  public FavoriteActivityService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public FavoriteActivityEntity createUserActivity(FavoriteActivityDto activityDto) {
    List<UserEntity> userList = userRepository.findAllByEmail(activityDto.userEmail());
    if (userList.isEmpty()) {
      throw new RuntimeException("User Not Found!");
    }

    Optional<ActivityEnum> activityEnum = ActivityEnum.findByTitle(activityDto.title());
    if (activityEnum.isEmpty()) {
      throw new RuntimeException("Activity not found!");
    }

    UserEntity user = userList.get(0);
    List<FavoriteActivityEntity> userFavList = favoriteActivityRepository.findAllByUser(user);
    if (userFavList.stream().anyMatch(ac -> ac.getActivityTitle().equals(activityDto.title()))) {
      throw new RuntimeException("Activity already registered to this user!");
    }

    FavoriteActivityEntity activityEntity = new FavoriteActivityEntity();
    activityEntity.setUser(user);
    activityEntity.setActivityTitle(activityEnum.get().getTitle());
    return favoriteActivityRepository.save(activityEntity);
  }
}
