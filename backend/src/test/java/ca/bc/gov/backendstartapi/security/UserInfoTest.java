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
            "123456789@idir",
            "Bilbo",
            "Baggings",
            "bilbo.baggings@gov.bc.ca",
            "Baggings, Bilbo LWRS:EX",
            "BAGGINGS",
            null,
            IdentityProvider.IDIR,
            new HashSet<>());

    Assertions.assertNotNull(userInfo);
    Assertions.assertEquals("Bilbo", userInfo.firstName());
    Assertions.assertEquals("Baggings", userInfo.lastName());
    Assertions.assertEquals("bilbo.baggings@gov.bc.ca", userInfo.email());
    Assertions.assertEquals("Baggings, Bilbo LWRS:EX", userInfo.displayName());
    Assertions.assertEquals("BAGGINGS", userInfo.idirUsername());
    Assertions.assertNull(userInfo.businessName());
    Assertions.assertEquals(IdentityProvider.IDIR, userInfo.identityProvider());
    Assertions.assertTrue(userInfo.roles().isEmpty());
  }
}
