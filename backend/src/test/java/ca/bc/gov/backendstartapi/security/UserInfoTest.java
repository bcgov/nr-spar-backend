package ca.bc.gov.backendstartapi.security;

import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserInfoTest {

  @Test
  @DisplayName("createUserInfo")
  void createUserInfo() {
    UserInfo userInfo =
        new UserInfo(
            "Bilbo",
            "bilbo.baggings@gov.bc.ca",
            new HashSet<>(),
            "idir",
            "BAGGINGS",
            null,
            false,
            "Bilbo",
            "Baggings, Bilbo LWRS:EX",
            "Baggings");

    Assertions.assertNotNull(userInfo);
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
}
