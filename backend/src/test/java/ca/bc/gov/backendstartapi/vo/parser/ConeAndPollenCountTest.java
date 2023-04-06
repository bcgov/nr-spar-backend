package ca.bc.gov.backendstartapi.vo.parser;

import static ca.bc.gov.backendstartapi.enums.parser.ConeAndPollenCountHeader.CONE_COUNT;
import static ca.bc.gov.backendstartapi.enums.parser.ConeAndPollenCountHeader.PARENT_TREE_NUMBER;
import static ca.bc.gov.backendstartapi.enums.parser.ConeAndPollenCountHeader.POLLEN_CONTAMINATION;
import static ca.bc.gov.backendstartapi.enums.parser.ConeAndPollenCountHeader.POLLEN_COUNT;
import static ca.bc.gov.backendstartapi.enums.parser.ConeAndPollenCountHeader.SMP_SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ConeAndPollenCountTest extends CsvParsingResultTest {

  @Override
  @Test
  void testFromMapMethod() {
    var vo =
        ConeAndPollenCount.fromMap(
            Map.of(
                PARENT_TREE_NUMBER, 1,
                CONE_COUNT, 2,
                POLLEN_COUNT, 3,
                SMP_SUCCESS, 4,
                POLLEN_CONTAMINATION, 5));
    assertEquals(
        new ConeAndPollenCount(1, BigDecimal.valueOf(2d), BigDecimal.valueOf(3d), 4, 5), vo);
  }

  @Test
  void parentTreeNumberMustBePositive() {
    assertThrowsExactly(
        IllegalArgumentException.class,
        () -> new ConeAndPollenCount(0, BigDecimal.valueOf(1d), BigDecimal.valueOf(1d), 1, 1));
  }

  @Test
  void coneCountMustBeNonNegative() {
    assertThrowsExactly(
        IllegalArgumentException.class,
        () -> new ConeAndPollenCount(1, BigDecimal.valueOf(-1d), BigDecimal.valueOf(1d), 1, 1));
  }

  @Test
  void pollenCountMustBeNonNegative() {
    assertThrowsExactly(
        IllegalArgumentException.class,
        () -> new ConeAndPollenCount(1, BigDecimal.valueOf(1d), BigDecimal.valueOf(-1d), 1, 1));
  }

  @Test
  void smpSuccessMustBeNonNegative() {
    assertThrowsExactly(
        IllegalArgumentException.class,
        () -> new ConeAndPollenCount(1, BigDecimal.valueOf(1d), BigDecimal.valueOf(1d), -1, 1));
  }

  @Test
  void pollenContaminationMustBeNonNegative() {
    assertThrowsExactly(
        IllegalArgumentException.class,
        () -> new ConeAndPollenCount(1, BigDecimal.valueOf(1d), BigDecimal.valueOf(1d), 1, -1));
  }
}
