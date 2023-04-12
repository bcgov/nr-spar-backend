package ca.bc.gov.backendstartapi.service.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import ca.bc.gov.backendstartapi.enums.parser.SmpMixHeader;
import ca.bc.gov.backendstartapi.exception.CsvTableParsingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
class CsvTableHeaderParserTest {

  private final CsvTableHeaderParser<SmpMixHeader> parser =
      new CsvTableHeaderParser<>(SmpMixHeader.class);

  @Test
  void testParsingOrder() {
    var headers = parser.parse("Pollen volume (ml),Parent tree number");
    assertEquals(List.of(SmpMixHeader.POLLEN_VOLUME_ML, SmpMixHeader.PARENT_TREE_NUMBER), headers);
  }

  @Test
  void blankColumns() {
    assertThrowsExactly(CsvTableParsingException.class, () -> parser.parse("Pollen volume (ml),"));
  }
}
