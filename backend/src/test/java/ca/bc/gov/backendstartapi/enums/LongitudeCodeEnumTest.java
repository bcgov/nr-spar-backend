package ca.bc.gov.backendstartapi.enums;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LongitudeCodeEnumTest {

  @Test
  @DisplayName("getByCodeTest")
  void getByCodeTest() {
    Optional<LongitudeCodeEnum> longitudeEnumEast = LongitudeCodeEnum.getByCode('E');
    Assertions.assertFalse(longitudeEnumEast.isEmpty());
    Assertions.assertEquals(longitudeEnumEast.get(), LongitudeCodeEnum.EAST);

    Optional<LongitudeCodeEnum> latitudeEnumSouth = LongitudeCodeEnum.getByCode('W');
    Assertions.assertFalse(latitudeEnumSouth.isEmpty());
    Assertions.assertEquals(latitudeEnumSouth.get(), LongitudeCodeEnum.WEST);

    Optional<LongitudeCodeEnum> latitudeEnumEmpty = LongitudeCodeEnum.getByCode('Z');
    Assertions.assertTrue(latitudeEnumEmpty.isEmpty());
  }
}
