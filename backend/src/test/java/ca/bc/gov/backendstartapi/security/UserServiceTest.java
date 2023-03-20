package ca.bc.gov.backendstartapi.security;

import static org.mockito.Mockito.when;

import ca.bc.gov.backendstartapi.exception.UserNotFoundException;
import ca.bc.gov.backendstartapi.repository.UserProfileRepository;
import java.util.HashSet;
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

  @Mock UserProfileRepository userProfileRepository;

  @Mock UserAuthenticationHelper userAuthenticationHelper;

  private LoggedUserService loggedUserService;

  private UserInfo userInfo;

  @BeforeEach
  void setup() {
    loggedUserService = new LoggedUserService(userAuthenticationHelper);
    userInfo =
        new UserInfo(
            "123456789@idir",
            "User",
            "Test",
            "user@test.com",
            "Test, User: LWRS:EX",
            "USERT",
            null,
            IdentityProvider.IDIR,
            new HashSet<>());
  }

  @Test
  @DisplayName("getLoggedUserEmailTest")
  void getLoggedUserEmailTest() {
    when(userAuthenticationHelper.getUserInfo()).thenReturn(Optional.of(userInfo));

    String userEmail = loggedUserService.getLoggedUserEmail();

    Assertions.assertEquals("user@test.com", userEmail);
  }

  @Test
  @DisplayName("getLoggedUserEmailExceptionTest")
  void getLoggedUserEmailExceptionTest() {
    Exception e =
        Assertions.assertThrows(
            UserNotFoundException.class,
            () -> {
              loggedUserService.getLoggedUserEmail();
            });

    Assertions.assertEquals("404 NOT_FOUND \"User not registered!\"", e.getMessage());
  }

  @Test
  @DisplayName("getLoggerUserInfoTest")
  void getLoggerUserInfoTest() {
    when(userAuthenticationHelper.getUserInfo()).thenReturn(Optional.of(userInfo));

    Optional<UserInfo> userInfoOp = loggedUserService.getLoggedUserInfo();

    Assertions.assertTrue(userInfoOp.isPresent());
  }

  @Test
  void createUserService() {
    LoggedUserService loggedUserService1 = new LoggedUserService();
    loggedUserService1.setUserAuthenticationHelper(userAuthenticationHelper);
  }
}
