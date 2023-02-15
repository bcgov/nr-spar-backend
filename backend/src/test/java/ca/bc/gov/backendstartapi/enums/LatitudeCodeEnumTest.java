package ca.bc.gov.backendstartapi.enums;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LatitudeCodeEnumTest {

  @Test
  @DisplayName("getByCodeTest")
  void getByCodeTest() {
    Optional<LatitudeCodeEnum> latitudeEnumNorth = LatitudeCodeEnum.getByCode('N');
    Assertions.assertFalse(latitudeEnumNorth.isEmpty());
    Assertions.assertEquals(latitudeEnumNorth.get(), LatitudeCodeEnum.NORTH);

    Optional<LatitudeCodeEnum> latitudeEnumSouth = LatitudeCodeEnum.getByCode('S');
    Assertions.assertFalse(latitudeEnumSouth.isEmpty());
    Assertions.assertEquals(latitudeEnumSouth.get(), LatitudeCodeEnum.SOUTH);

    Optional<LatitudeCodeEnum> latitudeEnumEmpty = LatitudeCodeEnum.getByCode('Z');
    Assertions.assertTrue(latitudeEnumEmpty.isEmpty());
  }
}
