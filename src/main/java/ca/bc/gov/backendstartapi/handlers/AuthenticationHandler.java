package ca.bc.gov.backendstartapi.handlers;

import ca.bc.gov.backendstartapi.security.UserInfo;
import java.util.HashSet;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationHandler {

  public UserInfo getUserInfo() {
    UserInfo userInfo = new UserInfo();

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.isAuthenticated()) {
      Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
      userInfo.setName(jwtPrincipal.getClaimAsString("name"));
      userInfo.setEmail(jwtPrincipal.getClaimAsString("email"));
      userInfo.setIdentityProvider(jwtPrincipal.getClaimAsString("identity_provider"));
      userInfo.setIdirUsername(jwtPrincipal.getClaimAsString("idir_username"));

      String emailVerified = jwtPrincipal.getClaimAsString("email_verified");
      userInfo.setEmailVerified(Boolean.valueOf(emailVerified));
      userInfo.setGivenName(jwtPrincipal.getClaimAsString("given_name"));
      userInfo.setDisplayName(jwtPrincipal.getClaimAsString("display_name"));
      userInfo.setFamilyName(jwtPrincipal.getClaimAsString("family_name"));

      userInfo.setRoles(new HashSet<>());
      if (jwtPrincipal.getClaims().containsKey("client_roles")) {
        List<String> clientRoles = jwtPrincipal.getClaimAsStringList("client_roles");
        userInfo.setRoles(new HashSet<>(clientRoles));
      }
    }

    return userInfo;
  }
}
