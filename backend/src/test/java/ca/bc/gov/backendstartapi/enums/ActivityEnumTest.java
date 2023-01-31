package ca.bc.gov.backendstartapi.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ActivityEnumTest {

  @Test
  @DisplayName("valueOfTest")
  void fromStringTest() {
    ActivityEnum seedlotRegistration = ActivityEnum.valueOf("SEEDLOT_REGISTRATION");
    Assertions.assertNotNull(seedlotRegistration);
    Assertions.assertEquals(ActivityEnum.SEEDLOT_REGISTRATION, seedlotRegistration);

    ActivityEnum parentTreeOrchard = ActivityEnum.valueOf("PARENT_TREE_ORCHARD");
    Assertions.assertNotNull(parentTreeOrchard);
    Assertions.assertEquals(ActivityEnum.PARENT_TREE_ORCHARD, parentTreeOrchard);

    ActivityEnum seedlingRequest = ActivityEnum.valueOf("SEEDLING_REQUEST");
    Assertions.assertNotNull(seedlingRequest);
    Assertions.assertEquals(ActivityEnum.SEEDLING_REQUEST, seedlingRequest);
  }
}
