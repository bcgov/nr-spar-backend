package ca.bc.gov.backendstartapi.enums;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConeCollectionMethodEnumTest {

  @Test
  @DisplayName("getByCodeTest")
  void getByCodeTest() {
    Optional<ConeCollectionMethodEnum> aerial01 = ConeCollectionMethodEnum.getByCode("01");
    Assertions.assertFalse(aerial01.isEmpty());
    Assertions.assertEquals(aerial01.get(), ConeCollectionMethodEnum.AERIAL_RAKING);

    Optional<ConeCollectionMethodEnum> aerial02 = ConeCollectionMethodEnum.getByCode("02");
    Assertions.assertFalse(aerial02.isEmpty());
    Assertions.assertEquals(aerial02.get(), ConeCollectionMethodEnum.AERIAL_CLIPPING_TOPPING);

    Optional<ConeCollectionMethodEnum> felled03 = ConeCollectionMethodEnum.getByCode("03");
    Assertions.assertFalse(felled03.isEmpty());
    Assertions.assertEquals(felled03.get(), ConeCollectionMethodEnum.FELLED_TREES);

    Optional<ConeCollectionMethodEnum> climbing04 = ConeCollectionMethodEnum.getByCode("04");
    Assertions.assertFalse(climbing04.isEmpty());
    Assertions.assertEquals(climbing04.get(), ConeCollectionMethodEnum.CLIMBING);

    Optional<ConeCollectionMethodEnum> squirrel05 = ConeCollectionMethodEnum.getByCode("05");
    Assertions.assertFalse(squirrel05.isEmpty());
    Assertions.assertEquals(squirrel05.get(), ConeCollectionMethodEnum.SQUIRREL_CACHE);

    Optional<ConeCollectionMethodEnum> ground06 = ConeCollectionMethodEnum.getByCode("06");
    Assertions.assertFalse(ground06.isEmpty());
    Assertions.assertEquals(ground06.get(), ConeCollectionMethodEnum.GROUNG_LADDER_HYDRAULIC_LIFT);

    Optional<ConeCollectionMethodEnum> unknown07 = ConeCollectionMethodEnum.getByCode("07");
    Assertions.assertFalse(unknown07.isEmpty());
    Assertions.assertEquals(unknown07.get(), ConeCollectionMethodEnum.UNKNOWN);

    Optional<ConeCollectionMethodEnum> squirrel08 = ConeCollectionMethodEnum.getByCode("08");
    Assertions.assertFalse(squirrel08.isEmpty());
    Assertions.assertEquals(
        squirrel08.get(), ConeCollectionMethodEnum.SQUIRREL_HARVESTING_DROPPING);

    Optional<ConeCollectionMethodEnum> latitudeEnumEmpty = ConeCollectionMethodEnum.getByCode("0");
    Assertions.assertTrue(latitudeEnumEmpty.isEmpty());

    Optional<ConeCollectionMethodEnum> latitudeEnumNull = ConeCollectionMethodEnum.getByCode(null);
    Assertions.assertTrue(latitudeEnumNull.isEmpty());
  }
}
