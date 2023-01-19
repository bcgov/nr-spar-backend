package ca.bc.gov.backendstartapi.service;

import ca.bc.gov.backendstartapi.entity.UserEntity;
import ca.bc.gov.backendstartapi.exception.UserNotFoundException;
import ca.bc.gov.backendstartapi.repository.UserRepository;
import ca.bc.gov.backendstartapi.security.UserAuthenticationHelper;
import ca.bc.gov.backendstartapi.security.UserInfo;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** This class contains all user related methods and artifacts. */
@Setter
@Service
@NoArgsConstructor
public class UserService {

  private UserRepository userRepository;

  private UserAuthenticationHelper userAuthenticationHelper;

  /**
   * Create a {@link UserService} instance.
   *
   * @param userRepository a {@link UserRepository} instance
   * @param userAuthenticationHelper a {@link UserAuthenticationHelper} instance
   */
  @Autowired
  public UserService(
      UserRepository userRepository, UserAuthenticationHelper userAuthenticationHelper) {
    this.userRepository = userRepository;
    this.userAuthenticationHelper = userAuthenticationHelper;
  }

  /**
   * Get the logged user email address.
   *
   * @return a {@link String} containing the email address if logged in. Or an empty string
   *     otherwise.
   */
  public String getLoggedUserEmail() {
    Optional<UserInfo> userInfo = userAuthenticationHelper.getUserInfo();
    if (userInfo.isEmpty()) {
      throw new UserNotFoundException();
    }

    return userInfo.get().email();
  }

  /**
   * Get all user info included in the JWT decoded token.
   *
   * @return an optional of {@link UserInfo}
   */
  public Optional<UserInfo> getLoggerUserInfo() {
    return userAuthenticationHelper.getUserInfo();
  }

  /**
   * Get the system entity related to the logged user.
   *
   * @return an optional of UserEntity.
   */
  public UserEntity getLoggerUserEntity() {
    String loggedUserEmail = getLoggedUserEmail();

    // Note: since the user table allows repeated emails, the 'find' method should return
    // a list instead of only one.
    List<UserEntity> userList = userRepository.findAllByEmail(loggedUserEmail);
    if (userList.isEmpty()) {
      throw new UserNotFoundException();
    }

    return userList.get(0);
  }
}
