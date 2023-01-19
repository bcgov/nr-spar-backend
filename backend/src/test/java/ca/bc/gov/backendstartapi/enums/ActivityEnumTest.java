package ca.bc.gov.backendstartapi.enums;

import java.util.Optional;
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
    Optional<ActivityEnum> seedlotRegistration = ActivityEnum.getByTitle(title);
    Assertions.assertTrue(seedlotRegistration.isPresent());

    ActivityEnum activityEnum = seedlotRegistration.get();
    Assertions.assertEquals("SoilMoistureField", activityEnum.getIconName());
    Assertions.assertEquals("Seedlot registration", activityEnum.getTitle());
    Assertions.assertEquals(DESCRIPTION, activityEnum.getDescription());
    Assertions.assertEquals("/seedlot-registration", activityEnum.getPage());

    Optional<ActivityEnum> notExisting = ActivityEnum.getByTitle("Nothing");
    Assertions.assertFalse(notExisting.isPresent());

    ActivityEnum myEnum = ActivityEnum.valueOf("SEEDLOT_REGISTRATION");

    String enumToString = "ActivityEnum.SEEDLOT_REGISTRATION("
        + "iconName=SoilMoistureField, "
        + "title=Seedlot registration, "
        + "description=Start a new registration or check on existing seedlots registrations, "
        + "page=/seedlot-registration)";
    Assertions.assertEquals(enumToString, myEnum.toString());
  }
}
