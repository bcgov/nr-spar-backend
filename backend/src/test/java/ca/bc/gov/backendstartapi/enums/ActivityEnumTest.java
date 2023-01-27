package ca.bc.gov.backendstartapi.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ActivityEnumTest {

  private static final String DESCRIPTION =
      "Start a new registration or check on existing seedlots registrations";

  @Test
  @DisplayName("valueOfTest")
  void fromStringTest() {
    ActivityEnum seedlotRegistration = ActivityEnum.valueOf("SEEDLOT_REGISTRATION");
    Assertions.assertNotNull(seedlotRegistration);
    Assertions.assertEquals(seedlotRegistration, ActivityEnum.SEEDLOT_REGISTRATION);

    ActivityEnum parentTreeOrchard = ActivityEnum.valueOf("PARENT_TREE_ORCHARD");
    Assertions.assertNotNull(parentTreeOrchard);
    Assertions.assertEquals(parentTreeOrchard, ActivityEnum.PARENT_TREE_ORCHARD);

    ActivityEnum seedlingRequest = ActivityEnum.valueOf("SEEDLING_REQUEST");
    Assertions.assertNotNull(seedlingRequest);
    Assertions.assertEquals(seedlingRequest, ActivityEnum.SEEDLING_REQUEST);
  }
}
