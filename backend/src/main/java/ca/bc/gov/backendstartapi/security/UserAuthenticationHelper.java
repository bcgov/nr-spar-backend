package ca.bc.gov.backendstartapi.security;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

/** This class contains helper methods to retrieved authenticated user. */
@Slf4j
@Component
public class UserAuthenticationHelper {

  /**
   * Get the logged user information.
   *
   * @return An Optional of {@link UserInfo} with all information from JWT token, if logged in or
   *     empty Optional otherwise.
   */
  public Optional<UserInfo> getUserInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication.isAuthenticated()) {
      Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();

      Set<String> roles = new HashSet<>();
      if (jwtPrincipal.getClaims().containsKey("client_roles")) {
        roles = new HashSet<>(jwtPrincipal.getClaimAsStringList("client_roles"));
      }

      UserInfo userInfo =
          new UserInfo(
              jwtPrincipal.getClaimAsString("sub"),
              jwtPrincipal.getClaimAsString("given_name"),
              jwtPrincipal.getClaimAsString("family_name"),
              jwtPrincipal.getClaimAsString("email"),
              jwtPrincipal.getClaimAsString("display_name"),
              jwtPrincipal.getClaimAsString("idir_username"),
              jwtPrincipal.getClaimAsString("bceid_business_name"),
              jwtPrincipal.getClaimAsString("identity_provider"),
              roles);

      return Optional.of(userInfo);
    }

    log.info("User not authenticated!");
    return Optional.empty();
  }
}
