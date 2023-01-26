package ca.bc.gov.backendstartapi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import ca.bc.gov.backendstartapi.dto.FavouriteActivityCreateDto;
import ca.bc.gov.backendstartapi.dto.FavouriteActivityUpdateDto;
import ca.bc.gov.backendstartapi.entity.FavouriteActivityEntity;
import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import ca.bc.gov.backendstartapi.exception.ActivityNotFoundException;
import ca.bc.gov.backendstartapi.exception.FavoriteActivityExistsToUser;
import ca.bc.gov.backendstartapi.repository.FavouriteActivityRepository;
import ca.bc.gov.backendstartapi.security.LoggedUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class FavouriteActivityServiceTest {

  @Mock FavouriteActivityRepository favouriteActivityRepository;

  @Mock LoggedUserService loggedUserService;

  private FavouriteActivityService favouriteActivityService;

  private static final String USER_ID = "123456789123456789@idir";

  @BeforeEach
  void setup() {
    favouriteActivityService =
        new FavouriteActivityService(loggedUserService, favouriteActivityRepository);
  }

  @Test
  @DisplayName("createUserActivityTest")
  void createUserActivityTest() {
    when(loggedUserService.getLoggedUserId()).thenReturn(USER_ID);

    FavouriteActivityEntity entity = new FavouriteActivityEntity();
    entity.setActivity(ActivityEnum.SEEDLING_REQUEST);
    entity.setHighlighted(false);
    entity.setEnabled(true);
    when(favouriteActivityRepository.save(any())).thenReturn(entity);

    FavouriteActivityCreateDto createDto =
        new FavouriteActivityCreateDto(ActivityEnum.SEEDLING_REQUEST);
    FavouriteActivityEntity entitySaved = favouriteActivityService.createUserActivity(createDto);

    Assertions.assertNotNull(entitySaved);
    Assertions.assertEquals(ActivityEnum.SEEDLING_REQUEST, entitySaved.getActivity());
    Assertions.assertFalse(entitySaved.getHighlighted());
    Assertions.assertTrue(entitySaved.getEnabled());
  }

  @Test
  @DisplayName("createUserActivityExceptionTest")
  void createUserActivityExceptionTest() {
    when(loggedUserService.getLoggedUserId()).thenReturn(USER_ID);

    FavouriteActivityEntity entity = new FavouriteActivityEntity();
    entity.setActivity(ActivityEnum.SEEDLING_REQUEST);
    entity.setHighlighted(false);
    entity.setEnabled(true);
    when(favouriteActivityRepository.save(any())).thenReturn(entity);

    FavouriteActivityCreateDto createDto = new FavouriteActivityCreateDto(null);

    Exception notFoundExc =
        Assertions.assertThrows(
            ActivityNotFoundException.class,
            () -> favouriteActivityService.createUserActivity(createDto));

    Assertions.assertEquals("404 NOT_FOUND \"Activity don't exist!\"", notFoundExc.getMessage());

    List<FavouriteActivityEntity> userFavList = List.of(entity);
    when(favouriteActivityRepository.findAllByUserId(any())).thenReturn(userFavList);

    FavouriteActivityCreateDto createAnotherDto =
        new FavouriteActivityCreateDto(ActivityEnum.SEEDLING_REQUEST);

    Exception activityExists =
        Assertions.assertThrows(
            FavoriteActivityExistsToUser.class,
            () -> favouriteActivityService.createUserActivity(createAnotherDto));

    Assertions.assertEquals(
        "400 BAD_REQUEST \"Activity already registered to this user!\"",
        activityExists.getMessage());
  }

  @Test
  @DisplayName("getAllUserFavoriteActivitiesTest")
  void getAllUserFavoriteActivitiesTest() {
    when(loggedUserService.getLoggedUserId()).thenReturn(USER_ID);

    FavouriteActivityEntity activityOne = new FavouriteActivityEntity();
    FavouriteActivityEntity activityTwo = new FavouriteActivityEntity();
    List<FavouriteActivityEntity> activityEntities =
        new ArrayList<>(List.of(activityOne, activityTwo));
    when(favouriteActivityRepository.findAllByEnabledAndUserId(true, USER_ID))
        .thenReturn(activityEntities);

    List<FavouriteActivityEntity> entityList =
        favouriteActivityService.getAllUserFavoriteActivities();

    Assertions.assertFalse(entityList.isEmpty());
  }

  @Test
  @DisplayName("updateUserActivityTest")
  void updateUserActivityTest() {
    when(loggedUserService.getLoggedUserId()).thenReturn(USER_ID);

    FavouriteActivityEntity entity = new FavouriteActivityEntity();
    entity.setActivity(ActivityEnum.SEEDLING_REQUEST);
    entity.setHighlighted(false);
    entity.setEnabled(true);
    when(favouriteActivityRepository.findById(any())).thenReturn(Optional.of(entity));

    when(favouriteActivityRepository.save(any())).thenReturn(entity);

    FavouriteActivityUpdateDto updateDto = new FavouriteActivityUpdateDto(true, true);
    FavouriteActivityEntity saved = favouriteActivityService.updateUserActivity(1L, updateDto);

    Assertions.assertNotNull(saved);
  }

  @Test
  @DisplayName("updateUserActivityExceptionTest")
  void updateUserActivityExceptionTest() {
    when(loggedUserService.getLoggedUserId()).thenReturn(USER_ID);

    FavouriteActivityEntity entity = new FavouriteActivityEntity();
    entity.setActivity(ActivityEnum.SEEDLING_REQUEST);
    entity.setHighlighted(false);
    entity.setEnabled(true);
    when(favouriteActivityRepository.findById(any())).thenReturn(Optional.empty());

    when(favouriteActivityRepository.save(any())).thenReturn(entity);

    FavouriteActivityUpdateDto updateDto = new FavouriteActivityUpdateDto(true, true);

    Exception e =
        Assertions.assertThrows(
            ActivityNotFoundException.class,
            () -> favouriteActivityService.updateUserActivity(1L, updateDto));

    Assertions.assertEquals("404 NOT_FOUND \"Activity don't exist!\"", e.getMessage());
  }

  @Test
  @DisplayName("deleteUserActivityTest")
  void deleteUserActivityTest() {
    when(loggedUserService.getLoggedUserId()).thenReturn(USER_ID);

    FavouriteActivityEntity entity = new FavouriteActivityEntity();
    entity.setActivity(ActivityEnum.SEEDLING_REQUEST);
    entity.setHighlighted(false);
    entity.setEnabled(true);
    when(favouriteActivityRepository.findById(any())).thenReturn(Optional.of(entity));

    doNothing().when(favouriteActivityRepository).deleteById(any());

    favouriteActivityService.deleteUserActivity(1L);
  }

  @Test
  @DisplayName("deleteUserActivityExceptionTest")
  void deleteUserActivityExceptionTest() {
    when(loggedUserService.getLoggedUserId()).thenReturn(USER_ID);

    when(favouriteActivityRepository.findById(any())).thenReturn(Optional.empty());

    doNothing().when(favouriteActivityRepository).deleteById(any());

    Exception e =
        Assertions.assertThrows(
            ActivityNotFoundException.class,
            () -> favouriteActivityService.deleteUserActivity(1L));

    Assertions.assertEquals("404 NOT_FOUND \"Activity don't exist!\"", e.getMessage());
  }

  @Test
  @DisplayName("createFavoriteActivityService")
  void createFavoriteActivityService() {
    FavouriteActivityService activityService = new FavouriteActivityService();
    activityService.setLoggedUserService(loggedUserService);
    activityService.setFavouriteActivityRepository(favouriteActivityRepository);
  }
}
