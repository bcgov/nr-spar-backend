package ca.bc.gov.backendstartapi.security;

import ca.bc.gov.backendstartapi.exception.UserNotFoundException;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** This class contains all user related methods and artifacts. */
@Setter
@Service
@NoArgsConstructor
public class LoggedUserService {

  private UserAuthenticationHelper userAuthenticationHelper;

  /**
   * Create a {@link LoggedUserService} instance.
   *
   * @param userAuthenticationHelper a {@link UserAuthenticationHelper} instance
   */
  @Autowired
  public LoggedUserService(UserAuthenticationHelper userAuthenticationHelper) {
    this.userAuthenticationHelper = userAuthenticationHelper;
  }

  /**
   * Get all user info included in the JWT decoded token.
   *
   * @return an optional of {@link UserInfo}. It'll be empty then not logged in
   */
  public Optional<UserInfo> getLoggerUserInfo() {
    return userAuthenticationHelper.getUserInfo();
  }

  /**
   * Get the email address from the logged user.
   *
   * @return a String containing the email address if logged in
   * @throws UserNotFoundException when not logged in
   */
  public String getLoggedUserEmail() {
    Optional<UserInfo> userInfo = userAuthenticationHelper.getUserInfo();
    if (userInfo.isEmpty()) {
      throw new UserNotFoundException();
    }

    return userInfo.get().email();
  }

  /**
   * Get the ID from the logged user.
   *
   * @return a String containing the id if logged in. Or an empty string otherwise
   * @throws UserNotFoundException when not logged in
   */
  public String getLoggedUserId() {
    Optional<UserInfo> userInfo = userAuthenticationHelper.getUserInfo();
    if (userInfo.isEmpty()) {
      throw new UserNotFoundException();
    }

    return userInfo.get().id();
  }
}
