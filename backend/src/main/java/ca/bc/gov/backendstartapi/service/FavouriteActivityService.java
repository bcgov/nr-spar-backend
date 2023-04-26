package ca.bc.gov.backendstartapi.service;

import ca.bc.gov.backendstartapi.dto.FavouriteActivityCreateDto;
import ca.bc.gov.backendstartapi.dto.FavouriteActivityUpdateDto;
import ca.bc.gov.backendstartapi.entity.FavouriteActivityEntity;
import ca.bc.gov.backendstartapi.exception.FavoriteActivityExistsToUser;
import ca.bc.gov.backendstartapi.exception.InvalidActivityException;
import ca.bc.gov.backendstartapi.repository.FavouriteActivityRepository;
import ca.bc.gov.backendstartapi.security.LoggedUserService;
import java.util.List;
import java.util.Objects;
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
public class FavouriteActivityService {

  private FavouriteActivityRepository favouriteActivityRepository;

  private LoggedUserService loggedUserService;

  /**
   * Create a {@link FavouriteActivityService} instance.
   *
   * @param loggedUserService a {@link LoggedUserService} instance
   * @param favouriteActivityRepository a {@link FavouriteActivityRepository} instance
   */
  @Autowired
  public FavouriteActivityService(
      LoggedUserService loggedUserService,
      FavouriteActivityRepository favouriteActivityRepository) {
    this.loggedUserService = loggedUserService;
    this.favouriteActivityRepository = favouriteActivityRepository;
  }

  /**
   * Create a user's activity in the database.
   *
   * @param activityDto a {@link FavouriteActivityCreateDto} containing the activity title
   * @return the {@link FavouriteActivityEntity} created
   */
  public FavouriteActivityEntity createUserActivity(FavouriteActivityCreateDto activityDto) {
    String userId = loggedUserService.getLoggedUserId();
    log.info("Creating activity {} to user {}", activityDto.activity(), userId);

    if (Objects.isNull(activityDto.activity()) || activityDto.activity().isBlank()) {
      throw new InvalidActivityException();
    }

    List<FavouriteActivityEntity> userFavList = favouriteActivityRepository.findAllByUserId(userId);
    if (userFavList.stream().anyMatch(ac -> ac.getActivity().equals(activityDto.activity()))) {
      log.info("Activity {} already exists to user {}!", activityDto.activity(), userId);
      throw new FavoriteActivityExistsToUser();
    }

    FavouriteActivityEntity activityEntity = new FavouriteActivityEntity();
    activityEntity.setUserId(userId);
    activityEntity.setActivity(activityDto.activity());
    return favouriteActivityRepository.save(activityEntity);
  }

  /**
   * Retrieve all favorite activities to a specific user.
   *
   * @return a list of FavoriteActivityEntity or an empty list
   */
  public List<FavouriteActivityEntity> getAllUserFavoriteActivities() {
    String userId = loggedUserService.getLoggedUserId();
    log.info("Retrieving all favorite activities to user {}", userId);
    return favouriteActivityRepository.findAllByEnabledAndUserId(Boolean.TRUE, userId);
  }

  /**
   * Updates a user activity.
   *
   * @param id the {@link Long} value as the id of the activity to be updated
   * @param updateDto a {@link FavouriteActivityUpdateDto} containing the values to be updated
   * @return a {@link FavouriteActivityEntity} updated
   * @throws InvalidActivityException if the activity doesn't exist
   */
  public FavouriteActivityEntity updateUserActivity(Long id, FavouriteActivityUpdateDto updateDto) {
    String userId = loggedUserService.getLoggedUserId();

    log.info("Updating activity {} to user {}", id, userId);
    Optional<FavouriteActivityEntity> activityEntity = favouriteActivityRepository.findById(id);
    if (activityEntity.isEmpty()) {
      throw new InvalidActivityException();
    }

    FavouriteActivityEntity entity =
        activityEntity
            .get()
            .withHighlighted(updateDto.highlighted())
            .withEnabled(updateDto.enabled());

    return favouriteActivityRepository.save(entity);
  }

  /**
   * Deletes a user activity by the activity id number.
   *
   * @param id A {@link Long} value as the id of the activity
   */
  public void deleteUserActivity(Long id) {
    String userId = loggedUserService.getLoggedUserId();

    log.info("Deleting activity {} to user {}", id, userId);
    Optional<FavouriteActivityEntity> activityEntity = favouriteActivityRepository.findById(id);
    if (activityEntity.isEmpty()) {
      throw new InvalidActivityException();
    }

    favouriteActivityRepository.deleteById(id);
  }
}
