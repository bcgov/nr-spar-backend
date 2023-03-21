package ca.bc.gov.backendstartapi.service.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.bc.gov.backendstartapi.enums.parser.SmpMixHeader;
import ca.bc.gov.backendstartapi.vo.parser.SmpMixVolume;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class CsvTableRowParserTest {

  private final CsvTableRowParser<SmpMixHeader, SmpMixVolume> parser =
      new CsvTableRowParser<>(SmpMixVolume::fromMap);

  @Test
  void testParsing() {
    var row =
        parser.parse(
            "1,3.0", List.of(SmpMixHeader.PARENT_TREE_NUMBER, SmpMixHeader.POLLEN_VOLUME_ML));
    assertEquals(new SmpMixVolume(1, BigDecimal.valueOf(3.0)), row);
  }

  @Test
  void testEmptyCellsValuedAsZero() {
    var row =
        parser.parse("1,", List.of(SmpMixHeader.PARENT_TREE_NUMBER, SmpMixHeader.POLLEN_VOLUME_ML));
    assertEquals(new SmpMixVolume(1, BigDecimal.valueOf(0d)), row);
  }
}
