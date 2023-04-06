package ca.bc.gov.backendstartapi.vo.parser;

import static ca.bc.gov.backendstartapi.enums.parser.SmpMixHeader.PARENT_TREE_NUMBER;
import static ca.bc.gov.backendstartapi.enums.parser.SmpMixHeader.POLLEN_VOLUME_ML;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.Test;

class SmpMixVolumeTest extends CsvParsingResultTest {

  @Override
  @Test
  void testFromMapMethod() {
    var vo = SmpMixVolume.fromMap(Map.of(PARENT_TREE_NUMBER, 1, POLLEN_VOLUME_ML, 2d));
    assertEquals(new SmpMixVolume(1, BigDecimal.valueOf(2d)), vo);
  }

  @Test
  void parentTreeNumberMustBePositive() {
    assertThrowsExactly(
        IllegalArgumentException.class, () -> new SmpMixVolume(0, BigDecimal.valueOf(1d)));
  }

  @Test
  void pollenVolumeMustBeNonNegative() {
    assertThrowsExactly(
        IllegalArgumentException.class, () -> new SmpMixVolume(1, BigDecimal.valueOf(-1d)));
  }
}
