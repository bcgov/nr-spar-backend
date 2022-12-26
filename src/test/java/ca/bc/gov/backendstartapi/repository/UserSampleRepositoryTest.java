package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.dto.UserSampleDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserSampleRepositoryTest {

  private final UserSampleRepository userSampleRepository = new UserSampleRepository();
  private static final String FIRST_NAME = "Ricardo";
  private static final String LAST_NAME = "Campos";

  private static final UserSampleDto USER_DTO = new UserSampleDto(FIRST_NAME, LAST_NAME);

  @Test
  @DisplayName("Save user into repository")
  void saveTest() {
    UserSampleDto saved = userSampleRepository.save(USER_DTO);

    Assertions.assertNotNull(saved);
    Assertions.assertEquals(FIRST_NAME, saved.firstName());
    Assertions.assertEquals(LAST_NAME, saved.lastName());
  }

  @Test
  @DisplayName("Find by first name")
  void findByFirstNameTest() {
    userSampleRepository.save(USER_DTO);

    List<UserSampleDto> userList = userSampleRepository.findAllByFirstName(FIRST_NAME);
    Assertions.assertEquals(1, userList.size());
    Assertions.assertEquals(FIRST_NAME, userList.get(0).firstName());
    Assertions.assertEquals(LAST_NAME, userList.get(0).lastName());
  }

  @Test
  @DisplayName("Find by last name")
  void findByLastNameTest() {
    userSampleRepository.save(USER_DTO);

    List<UserSampleDto> userList = userSampleRepository.findAllByLastName(LAST_NAME);
    Assertions.assertEquals(1, userList.size());
    Assertions.assertEquals(FIRST_NAME, userList.get(0).firstName());
    Assertions.assertEquals(LAST_NAME, userList.get(0).lastName());
  }

  @Test
  @DisplayName("Find by first and last name")
  void findTest() {
    userSampleRepository.save(USER_DTO);

    Optional<UserSampleDto> userList = userSampleRepository.find(FIRST_NAME, LAST_NAME);
    Assertions.assertFalse(userList.isEmpty());
    Assertions.assertEquals(FIRST_NAME, userList.get().firstName());
    Assertions.assertEquals(LAST_NAME, userList.get().lastName());
  }

  @Test
  @DisplayName("Find All users test")
  void findAllTest() {
    userSampleRepository.save(USER_DTO);

    Collection<UserSampleDto> userList = userSampleRepository.findAll();
    Assertions.assertEquals(1, userList.size());
    UserSampleDto userSampleDto = new ArrayList<>(userList).get(0);
    Assertions.assertEquals(FIRST_NAME, userSampleDto.firstName());
    Assertions.assertEquals(LAST_NAME, userSampleDto.lastName());
  }

  @Test
  @DisplayName("Delete user from the repository")
  void deleteTest() {
    userSampleRepository.save(USER_DTO);

    UserSampleDto deleted = userSampleRepository.delete(USER_DTO);
    Assertions.assertNotNull(deleted);
    Assertions.assertEquals(FIRST_NAME, deleted.firstName());
    Assertions.assertEquals(LAST_NAME, deleted.lastName());

    Optional<UserSampleDto> find = userSampleRepository.find(FIRST_NAME, LAST_NAME);
    Assertions.assertTrue(find.isEmpty());
  }
}
