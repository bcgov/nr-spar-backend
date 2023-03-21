package ca.bc.gov.backendstartapi.service.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.bc.gov.backendstartapi.enums.parser.SmpMixHeader;
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
}
