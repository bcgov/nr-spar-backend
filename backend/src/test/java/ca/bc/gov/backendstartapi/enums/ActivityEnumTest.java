package ca.bc.gov.backendstartapi.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ActivityEnumTest {

  private static final String DESCRIPTION =
      "Start a new registration or check on existing seedlots registrations";

  @Test
  @DisplayName("getPropsTest")
  void getPropsTest() {
    ActivityEnum seedlotRegistration = ActivityEnum.SEEDLOT_REGISTRATION;

    Assertions.assertEquals("SoilMoistureField", seedlotRegistration.getIconName());
    Assertions.assertEquals("Seedlot registration", seedlotRegistration.getTitle());
    Assertions.assertEquals(DESCRIPTION, seedlotRegistration.getDescription());
    Assertions.assertEquals("/seedlot-registration", seedlotRegistration.getPage());
  }

  @Test
  @DisplayName("getByTitleTest")
  void getByTitleTest() {
    String title = "Seedlot registration";
    ActivityEnum seedlotRegistration = ActivityEnum.findByTitle(title);

    Assertions.assertNotNull(seedlotRegistration);
    Assertions.assertEquals("SoilMoistureField", seedlotRegistration.getIconName());
    Assertions.assertEquals("Seedlot registration", seedlotRegistration.getTitle());
    Assertions.assertEquals(DESCRIPTION, seedlotRegistration.getDescription());
    Assertions.assertEquals("/seedlot-registration", seedlotRegistration.getPage());

    ActivityEnum notExisting = ActivityEnum.findByTitle("Nothing");
    Assertions.assertNull(notExisting);
  }
}
