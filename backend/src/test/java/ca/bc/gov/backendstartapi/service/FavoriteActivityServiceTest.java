package ca.bc.gov.backendstartapi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import ca.bc.gov.backendstartapi.dto.FavoriteActivityCreateDto;
import ca.bc.gov.backendstartapi.dto.FavoriteActivityUpdateDto;
import ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity;
import ca.bc.gov.backendstartapi.entity.UserEntity;
import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import ca.bc.gov.backendstartapi.exception.ActivityNotFoundException;
import ca.bc.gov.backendstartapi.exception.FavoriteActivityExistsToUser;
import ca.bc.gov.backendstartapi.repository.FavoriteActivityRepository;
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
class FavoriteActivityServiceTest {

  @Mock FavoriteActivityRepository favoriteActivityRepository;

  @Mock UserService userService;

  private FavoriteActivityService favoriteActivityService;

  @BeforeEach
  void setup() {
    favoriteActivityService = new FavoriteActivityService(userService, favoriteActivityRepository);
  }

  private UserEntity createUserEntity() {
    UserEntity user = new UserEntity();
    user.setId(1L);
    user.setEmail("user@test.com");
    return user;
  }

  @Test
  @DisplayName("createUserActivityTest")
  void createUserActivityTest() {
    when(userService.getLoggerUserEntity()).thenReturn(createUserEntity());

    String title = ActivityEnum.SEEDLING_REQUEST.getTitle();

    FavoriteActivityEntity entity = new FavoriteActivityEntity();
    entity.setActivityTitle(title);
    entity.setHighlighted(false);
    entity.setEnabled(true);
    when(favoriteActivityRepository.save(any())).thenReturn(entity);

    FavoriteActivityCreateDto createDto = new FavoriteActivityCreateDto(title);
    FavoriteActivityEntity entitySaved = favoriteActivityService.createUserActivity(createDto);

    Assertions.assertNotNull(entitySaved);
    Assertions.assertEquals(title, entitySaved.getActivityTitle());
    Assertions.assertFalse(entitySaved.getHighlighted());
    Assertions.assertTrue(entitySaved.getEnabled());
  }

  @Test
  @DisplayName("createUserActivityExceptionTest")
  void createUserActivityExceptionTest() {
    when(userService.getLoggerUserEntity()).thenReturn(createUserEntity());

    String title = ActivityEnum.SEEDLING_REQUEST.getTitle();

    FavoriteActivityEntity entity = new FavoriteActivityEntity();
    entity.setActivityTitle(title);
    entity.setHighlighted(false);
    entity.setEnabled(true);
    when(favoriteActivityRepository.save(any())).thenReturn(entity);

    FavoriteActivityCreateDto createDto = new FavoriteActivityCreateDto("NotExists");

    Exception notFoundExc =
        Assertions.assertThrows(
            ActivityNotFoundException.class,
            () -> {
              favoriteActivityService.createUserActivity(createDto);
            });

    Assertions.assertEquals("404 NOT_FOUND \"Activity don't exist!\"", notFoundExc.getMessage());

    List<FavoriteActivityEntity> userFavList = List.of(entity);
    when(favoriteActivityRepository.findAllByUser(any())).thenReturn(userFavList);

    FavoriteActivityCreateDto createAnotherDto = new FavoriteActivityCreateDto(title);

    Exception activityExists =
        Assertions.assertThrows(
            FavoriteActivityExistsToUser.class,
            () -> {
              favoriteActivityService.createUserActivity(createAnotherDto);
            });

    Assertions.assertEquals(
        "400 BAD_REQUEST \"Activity already registered to this user!\"",
        activityExists.getMessage());
  }

  @Test
  @DisplayName("getAllUserFavoriteActivitiesTest")
  void getAllUserFavoriteActivitiesTest() {
    when(userService.getLoggerUserEntity()).thenReturn(createUserEntity());

    FavoriteActivityEntity activityOne = new FavoriteActivityEntity();
    FavoriteActivityEntity activityTwo = new FavoriteActivityEntity();
    List<FavoriteActivityEntity> activityEntities =
        new ArrayList<>(List.of(activityOne, activityTwo));
    when(favoriteActivityRepository.findAllByEnabledAndUser(true, 1L)).thenReturn(activityEntities);

    List<FavoriteActivityEntity> entityList =
        favoriteActivityService.getAllUserFavoriteActivities();

    Assertions.assertFalse(entityList.isEmpty());
  }

  @Test
  @DisplayName("updateUserActivityTest")
  void updateUserActivityTest() {
    when(userService.getLoggerUserEntity()).thenReturn(createUserEntity());

    FavoriteActivityEntity entity = new FavoriteActivityEntity();
    entity.setActivityTitle(ActivityEnum.SEEDLING_REQUEST.getTitle());
    entity.setHighlighted(false);
    entity.setEnabled(true);
    when(favoriteActivityRepository.findById(any())).thenReturn(Optional.of(entity));

    when(favoriteActivityRepository.save(any())).thenReturn(entity);

    FavoriteActivityUpdateDto updateDto = new FavoriteActivityUpdateDto(true, true);
    FavoriteActivityEntity saved = favoriteActivityService.updateUserActivity(1L, updateDto);

    Assertions.assertNotNull(saved);
  }

  @Test
  @DisplayName("updateUserActivityExceptionTest")
  void updateUserActivityExceptionTest() {
    when(userService.getLoggerUserEntity()).thenReturn(createUserEntity());

    FavoriteActivityEntity entity = new FavoriteActivityEntity();
    entity.setActivityTitle(ActivityEnum.SEEDLING_REQUEST.getTitle());
    entity.setHighlighted(false);
    entity.setEnabled(true);
    when(favoriteActivityRepository.findById(any())).thenReturn(Optional.empty());

    when(favoriteActivityRepository.save(any())).thenReturn(entity);

    FavoriteActivityUpdateDto updateDto = new FavoriteActivityUpdateDto(true, true);

    Exception e =
        Assertions.assertThrows(
            ActivityNotFoundException.class,
            () -> {
              favoriteActivityService.updateUserActivity(1L, updateDto);
            });

    Assertions.assertEquals("404 NOT_FOUND \"Activity don't exist!\"", e.getMessage());
  }

  @Test
  @DisplayName("deleteUserActivityTest")
  void deleteUserActivityTest() {
    when(userService.getLoggerUserEntity()).thenReturn(createUserEntity());

    FavoriteActivityEntity entity = new FavoriteActivityEntity();
    entity.setActivityTitle(ActivityEnum.SEEDLING_REQUEST.getTitle());
    entity.setHighlighted(false);
    entity.setEnabled(true);
    when(favoriteActivityRepository.findById(any())).thenReturn(Optional.of(entity));

    doNothing().when(favoriteActivityRepository).deleteById(any());

    favoriteActivityService.deleteUserActivity(1L);
  }

  @Test
  @DisplayName("deleteUserActivityExceptionTest")
  void deleteUserActivityExceptionTest() {
    when(userService.getLoggerUserEntity()).thenReturn(createUserEntity());

    FavoriteActivityEntity entity = new FavoriteActivityEntity();
    entity.setActivityTitle(ActivityEnum.SEEDLING_REQUEST.getTitle());
    entity.setHighlighted(false);
    entity.setEnabled(true);
    when(favoriteActivityRepository.findById(any())).thenReturn(Optional.empty());

    doNothing().when(favoriteActivityRepository).deleteById(any());

    Exception e =
        Assertions.assertThrows(
            ActivityNotFoundException.class,
            () -> {
              favoriteActivityService.deleteUserActivity(1L);
            });

    Assertions.assertEquals("404 NOT_FOUND \"Activity don't exist!\"", e.getMessage());
  }

  @Test
  @DisplayName("createFavoriteActivityService")
  void createFavoriteActivityService() {
    FavoriteActivityService activityService = new FavoriteActivityService();
    activityService.setUserService(userService);
    activityService.setFavoriteActivityRepository(favoriteActivityRepository);
  }
}
