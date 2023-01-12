package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity;
import ca.bc.gov.backendstartapi.entity.UserEntity;
import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FavoriteActivityRepositoryTest {

  private static final String USER_TEST_EMAIL = "test.user@gov.bc.ca";

  @Autowired private FavoriteActivityRepository favoriteActivityRepository;

  @Autowired private UserRepository userRepository;

  // CREATE - POST
  @Test
  @DisplayName("createWithDefaultValuesTest")
  @Order(1)
  @Sql(scripts = {"classpath:sql_scripts/FavoriteActivityRepositoryTest_user.sql"})
  void createWithDefaultValuesTest() {
    List<UserEntity> users = userRepository.findAllByEmail(USER_TEST_EMAIL);
    Assertions.assertFalse(users.isEmpty());

    FavoriteActivityEntity activity = new FavoriteActivityEntity();
    activity.setUser(users.get(0));
    activity.setActivityTitle(ActivityEnum.SEEDLOT_REGISTRATION.getTitle());
    FavoriteActivityEntity created = favoriteActivityRepository.save(activity);

    Assertions.assertEquals(1L, created.getId());
    Assertions.assertEquals(
        ActivityEnum.SEEDLOT_REGISTRATION.getTitle(), created.getActivityTitle());
    Assertions.assertFalse(created.getHighlighted());
    Assertions.assertTrue(created.getEnabled());
  }

  @Test
  @DisplayName("createWithAllValuesTest")
  @Order(2)
  @Sql(scripts = {"classpath:sql_scripts/FavoriteActivityRepositoryTest_user.sql"})
  void createWithAllValuesTest() {
    List<UserEntity> users = userRepository.findAllByEmail(USER_TEST_EMAIL);
    Assertions.assertFalse(users.isEmpty());

    FavoriteActivityEntity activity = new FavoriteActivityEntity();
    activity.setUser(users.get(0));
    activity.setActivityTitle(ActivityEnum.SEEDLING_REQUEST.getTitle());
    activity.setHighlighted(true);
    activity.setEnabled(false);
    FavoriteActivityEntity created = favoriteActivityRepository.save(activity);

    Assertions.assertEquals(ActivityEnum.SEEDLING_REQUEST.getTitle(), created.getActivityTitle());
    Assertions.assertTrue(created.getHighlighted());
    Assertions.assertFalse(created.getEnabled());
  }

  @Test
  @DisplayName("preventFromDuplicateUserTest")
  @Order(3)
  void preventFromDuplicateUserTest() {
    //
  }

  @Test
  @DisplayName("findAllByUserTest")
  @Order(4)
  @Sql(scripts = {"classpath:sql_scripts/FavoriteActivityRepositoryTest_favorite.sql"})
  void findAllByUserTest() {
    List<UserEntity> userList = userRepository.findAllByEmail(USER_TEST_EMAIL);
    Assertions.assertFalse(userList.isEmpty());
    UserEntity user = userList.get(0);
    List<FavoriteActivityEntity> allByUser = favoriteActivityRepository.findAllByUser(user);
    Assertions.assertFalse(allByUser.isEmpty());
    Assertions.assertEquals(2, allByUser.size());
  }

  @Test
  @DisplayName("updateActivityTest")
  @Order(5)
  @Sql(scripts = {"classpath:sql_scripts/FavoriteActivityRepositoryTest_favorite.sql"})
  void updateActivityTest() {
    String title = "Activity one";
    Optional<FavoriteActivityEntity> getOne = favoriteActivityRepository.findByActivityTitle(title);
    Assertions.assertTrue(getOne.isPresent());

    FavoriteActivityEntity activity = getOne.get();
    Long activityId = activity.getId();
    Assertions.assertEquals("Activity one", activity.getActivityTitle());
    Assertions.assertFalse(activity.getHighlighted());
    Assertions.assertTrue(activity.getEnabled());

    activity.setEnabled(Boolean.FALSE);
    activity.setHighlighted(Boolean.TRUE);
    FavoriteActivityEntity saved = favoriteActivityRepository.save(activity);

    Assertions.assertEquals(activityId, saved.getId());
    Assertions.assertEquals("Activity one", saved.getActivityTitle());
    Assertions.assertTrue(saved.getHighlighted());
    Assertions.assertFalse(saved.getEnabled());
  }

}
