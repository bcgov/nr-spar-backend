package ca.bc.gov.backendstartapi.security;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

/** This class contains helper methods to retrieved authenticated user. */
@Slf4j
@Component
public class UserAuthenticationHelper {

  /**
   * Get the logged user information.
   *
   * @return An Optional of {@link UserInfo} with all information from JWT token, if logged in
   *     or empty Optional otherwise.
   */
  public Optional<UserInfo> getUserInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication.isAuthenticated()) {
      // For test cases, integration tests
      if (authentication.getPrincipal() instanceof User) {
        UserInfo userInfo =
            new UserInfo(
                "Test User",
                "user@test.com",
                new HashSet<>(),
                "idir",
                "test",
                "test",
                false,
                "User",
                "Test User",
                "Test");
        return Optional.of(userInfo);
      }

      Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();

      Set<String> roles = new HashSet<>();
      if (jwtPrincipal.getClaims().containsKey("client_roles")) {
        roles = new HashSet<>(jwtPrincipal.getClaimAsStringList("client_roles"));
      }

      UserInfo userInfo =
          new UserInfo(
              jwtPrincipal.getClaimAsString("name"),
              jwtPrincipal.getClaimAsString("email"),
              roles,
              jwtPrincipal.getClaimAsString("identity_provider"),
              jwtPrincipal.getClaimAsString("idir_username"),
              jwtPrincipal.getClaimAsString("businessbceid_username"),
              Boolean.valueOf(jwtPrincipal.getClaimAsString("email_verified")),
              jwtPrincipal.getClaimAsString("given_name"),
              jwtPrincipal.getClaimAsString("display_name"),
              jwtPrincipal.getClaimAsString("family_name"));

      return Optional.of(userInfo);
    }

    log.info("User not authenticated!");
    return Optional.empty();
  }
}
