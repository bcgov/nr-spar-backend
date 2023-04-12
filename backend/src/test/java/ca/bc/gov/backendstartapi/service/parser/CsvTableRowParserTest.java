package ca.bc.gov.backendstartapi.service.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import ca.bc.gov.backendstartapi.enums.parser.SmpMixHeader;
import ca.bc.gov.backendstartapi.exception.CsvTableParsingException;
import ca.bc.gov.backendstartapi.vo.parser.SmpMixVolume;
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
    assertEquals(new SmpMixVolume(1, 3.0), row);
  }

  @Test
  void testEmptyCellsValuedAsZero() {
    var row =
        parser.parse("1,", List.of(SmpMixHeader.PARENT_TREE_NUMBER, SmpMixHeader.POLLEN_VOLUME_ML));
    assertEquals(new SmpMixVolume(1, 0d), row);
  }

  @Test
  void notNumbers() {
    var exception =
        assertThrowsExactly(
            CsvTableParsingException.class,
            () ->
                parser.parse(
                    "1,two",
                    List.of(SmpMixHeader.PARENT_TREE_NUMBER, SmpMixHeader.POLLEN_VOLUME_ML)));
    assertEquals("1,two", exception.getMessage().split(": ")[1]);
  }

  @Test
  void moreColumnsThanHeaders() {
    assertThrowsExactly(
        CsvTableParsingException.class,
        () ->
            parser.parse(
                "1,2,3", List.of(SmpMixHeader.PARENT_TREE_NUMBER, SmpMixHeader.POLLEN_VOLUME_ML)));
  }

  @Test
  void moreHeadersThanColumns() {
    assertThrowsExactly(
        CsvTableParsingException.class,
        () ->
            parser.parse(
                "1", List.of(SmpMixHeader.PARENT_TREE_NUMBER, SmpMixHeader.POLLEN_VOLUME_ML)));
  }
}
