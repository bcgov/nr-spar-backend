package ca.bc.gov.backendstartapi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import ca.bc.gov.backendstartapi.dto.FavoriteActivityCreateDto;
import ca.bc.gov.backendstartapi.dto.FavoriteActivityUpdateDto;
import ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity;
import ca.bc.gov.backendstartapi.entity.UserEntity;
import ca.bc.gov.backendstartapi.enums.ActivityEnum;
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
  @DisplayName("getAllUserFavoriteActivitiesTest")
  void getAllUserFavoriteActivitiesTest() {
    when(userService.getLoggerUserEntity()).thenReturn(createUserEntity());

    FavoriteActivityEntity activityOne = new FavoriteActivityEntity();
    FavoriteActivityEntity activityTwo = new FavoriteActivityEntity();
    List<FavoriteActivityEntity> activityEntities =
        new ArrayList<>(List.of(activityOne, activityTwo));
    when(favoriteActivityRepository.findAllByEnabledAndUser(true, 1L))
        .thenReturn(activityEntities);

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
}
