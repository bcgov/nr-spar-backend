package ca.bc.gov.backendstartapi.security;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserAuthenticationHelperTest {

  private UserAuthenticationHelper userAuthenticationHelper;

  @BeforeEach
  void setup() {
    userAuthenticationHelper = new UserAuthenticationHelper();
  }

  @Test
  @DisplayName("getUserInfoTestSuccess")
  void getUserInfoTestSuccess() {
    Authentication authentication = mock(Authentication.class);
    SecurityContext securityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);

    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(authentication.isAuthenticated()).thenReturn(true);

    Jwt.Builder builder = Jwt.withTokenValue("myTokenValue");
    builder.header("alg", "HS256");
    builder.header("typ", "JWT");
    builder.claim("name", "Bilbo");
    builder.claim("email", "bilbo.baggings@gov.bc.ca");
    builder.claim("identity_provider", "idir");
    builder.claim("idir_username", "BAGGINGS");
    builder.claim("businessbceid_username", null);
    builder.claim("email_verified", "false");
    builder.claim("given_name", "Bilbo");
    builder.claim("display_name", "Baggings, Bilbo LWRS:EX");
    builder.claim("family_name", "Baggings");

    when(authentication.getPrincipal()).thenReturn(builder.build());

    Optional<UserInfo> userInfoOptional = userAuthenticationHelper.getUserInfo();
    Assertions.assertTrue(userInfoOptional.isPresent());

    UserInfo userInfo = userInfoOptional.get();
    Assertions.assertEquals("Bilbo", userInfo.name());
    Assertions.assertEquals("bilbo.baggings@gov.bc.ca", userInfo.email());
    Assertions.assertEquals("idir", userInfo.identityProvider());
    Assertions.assertEquals("BAGGINGS", userInfo.idirUsername());
    Assertions.assertNull(userInfo.businessbceidUsername());
    Assertions.assertFalse(userInfo.emailVerified());
    Assertions.assertEquals("Bilbo", userInfo.givenName());
    Assertions.assertEquals("Baggings, Bilbo LWRS:EX", userInfo.displayName());
    Assertions.assertEquals("Baggings", userInfo.familyName());
  }

  @Test
  @DisplayName("getUserInfoTestUserInstance")
  void getUserInfoTestUserInstance() {
    Authentication authentication = mock(Authentication.class);
    SecurityContext securityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);

    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(authentication.isAuthenticated()).thenReturn(true);

    User user = new User(
        "Gimli",
        "myAxe",
        true,
        false,
        false,
        true,
        List.of(new SimpleGrantedAuthority("ROLE_user_read"))
    );

    when(authentication.getPrincipal()).thenReturn(user);

    Optional<UserInfo> userInfoOptional = userAuthenticationHelper.getUserInfo();
    Assertions.assertTrue(userInfoOptional.isPresent());

    UserInfo userInfo = userInfoOptional.get();
    Assertions.assertEquals("Test User", userInfo.name());
    Assertions.assertEquals("user@test.com", userInfo.email());
    Assertions.assertEquals("idir", userInfo.identityProvider());
    Assertions.assertEquals("test", userInfo.idirUsername());
    Assertions.assertEquals("test", userInfo.businessbceidUsername());
    Assertions.assertFalse(userInfo.emailVerified());
    Assertions.assertEquals("User", userInfo.givenName());
    Assertions.assertEquals("Test User", userInfo.displayName());
    Assertions.assertEquals("Test", userInfo.familyName());
  }

  @Test
  @DisplayName("getUserInfoTestNotAuthenticated")
  void getUserInfoTestNotAuthenticated() {
    Authentication authentication = mock(Authentication.class);
    SecurityContext securityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);

    when(securityContext.getAuthentication()).thenReturn(authentication);

    Optional<UserInfo> userInfoOptional = userAuthenticationHelper.getUserInfo();
    Assertions.assertFalse(userInfoOptional.isPresent());
  }
}
