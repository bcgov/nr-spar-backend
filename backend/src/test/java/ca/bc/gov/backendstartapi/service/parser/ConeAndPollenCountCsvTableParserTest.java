package ca.bc.gov.backendstartapi.service.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import ca.bc.gov.backendstartapi.exception.CsvTableParsingException;
import ca.bc.gov.backendstartapi.vo.parser.ConeAndPollenCount;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
class ConeAndPollenCountCsvTableParserTest {

  final ClassLoader classLoader = getClass().getClassLoader();

  private final ConeAndPollenCountCsvTableParser parser;

  @Autowired
  ConeAndPollenCountCsvTableParserTest(ConeAndPollenCountCsvTableParser parser) {
    this.parser = parser;
  }

  @Test
  void mustAllowEmptyLineAtTheEnd() throws IOException {
    var mixes =
        parser.parse(
            new UrlResource(
                Objects.requireNonNull(
                    classLoader.getResource("csv/contribution/finalEmptyLine.csv"))));
    assertEquals(
        List.of(
            new ConeAndPollenCount(1, 2.3, 4d, 5, 6),
            new ConeAndPollenCount(7, 8.9, 10.11, 12, 13)),
        mixes);
  }

  @Test
  void headerMustBeFirstLine() {
    assertThrowsExactly(
        CsvTableParsingException.class,
        () ->
            parser.parse(
                new UrlResource(
                    Objects.requireNonNull(
                        classLoader.getResource("csv/contribution/firstLineNotHeader.csv")))));
  }

  @Test
  void missingHeaderValue() {
    MultipartFile file =
        new MockMultipartFile(
            "file",
            """
        Parent Tree number,Pollen count,Cone count
        1,2.0,3""".getBytes());
    var exception =
        assertThrowsExactly(CsvTableParsingException.class, () -> parser.parse(file.getResource()));
    assertEquals("SMP success, Pollen contamination", exception.getMessage().split(": ")[1]);
  }
}
