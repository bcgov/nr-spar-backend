package ca.bc.gov.backendstartapi.service.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import ca.bc.gov.backendstartapi.exception.CsvTableParsingException;
import ca.bc.gov.backendstartapi.vo.parser.SmpMixVolume;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.UrlResource;

@SpringBootTest
class SmpCalculationCsvTableParserTest {

  final ClassLoader classLoader = getClass().getClassLoader();

  private final SmpCalculationCsvTableParser parser;

  @Autowired
  SmpCalculationCsvTableParserTest(SmpCalculationCsvTableParser smpCalculationCsvTableParser) {
    parser = smpCalculationCsvTableParser;
  }

  @Test
  void mustAllowEmptyLineAtTheEnd() throws IOException {
    var mixes =
        parser.parse(
            new UrlResource(
                Objects.requireNonNull(classLoader.getResource("csv/smpmix/finalEmptyLine.csv"))));
    assertEquals(List.of(new SmpMixVolume(1, 2.5), new SmpMixVolume(2, 1.3)), mixes);
  }

  @Test
  void headerMustBeFirstLine() {
    assertThrowsExactly(
        CsvTableParsingException.class,
        () ->
            parser.parse(
                new UrlResource(
                    Objects.requireNonNull(
                        classLoader.getResource("csv/smpmix/firstLineNotHeader.csv")))));
  }
}
