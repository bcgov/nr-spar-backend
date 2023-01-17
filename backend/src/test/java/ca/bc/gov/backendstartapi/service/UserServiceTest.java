package ca.bc.gov.backendstartapi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.bc.gov.backendstartapi.entity.UserEntity;
import ca.bc.gov.backendstartapi.exception.UserNotFoundException;
import ca.bc.gov.backendstartapi.repository.UserRepository;
import ca.bc.gov.backendstartapi.security.UserAuthenticationHelper;
import ca.bc.gov.backendstartapi.security.UserInfo;
import java.util.HashSet;
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
class UserServiceTest {

  @Mock UserRepository userRepository;

  @Mock UserAuthenticationHelper userAuthenticationHelper;

  private UserService userService;

  private UserInfo userInfo;

  @BeforeEach
  void setup() {
    userService = new UserService(userRepository, userAuthenticationHelper);
    userInfo = new UserInfo("User", "user@test.com", new HashSet<>(),
        "idir", "USERT", "bceid", false,
        "User", "User", "Test");
  }

  @Test
  @DisplayName("getLoggedUserEmailTest")
  void getLoggedUserEmailTest() {
    when(userAuthenticationHelper.getUserInfo()).thenReturn(Optional.of(userInfo));

    String userEmail = userService.getLoggedUserEmail();

    Assertions.assertEquals("user@test.com", userEmail);
  }

  @Test
  @DisplayName("getLoggedUserEmailExceptionTest")
  void getLoggedUserEmailExceptionTest() {
    Exception e = Assertions.assertThrows(UserNotFoundException.class, () -> {
      userService.getLoggedUserEmail();
    });

    Assertions.assertEquals("User not registered!", e.getMessage());
  }

  @Test
  @DisplayName("getLoggerUserInfoTest")
  void getLoggerUserInfoTest() {
    when(userAuthenticationHelper.getUserInfo()).thenReturn(Optional.of(userInfo));

    Optional<UserInfo> userInfoOp = userService.getLoggerUserInfo();

    Assertions.assertTrue(userInfoOp.isPresent());
  }

  @Test
  @DisplayName("getLoggerUserEntityTest")
  void getLoggerUserEntityTest() {
    when(userAuthenticationHelper.getUserInfo()).thenReturn(Optional.of(userInfo));

    UserEntity userEntity = new UserEntity();
    when(userRepository.findAllByEmail(any())).thenReturn(List.of(userEntity));

    UserEntity user = userService.getLoggerUserEntity();

    Assertions.assertNotNull(user);

    // TODO: keep going from here!
  }
}
