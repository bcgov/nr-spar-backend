package ca.bc.gov.backendstartapi.service.parser;

import static java.util.function.Predicate.not;

import ca.bc.gov.backendstartapi.enums.parser.CsvParsingHeader;
import ca.bc.gov.backendstartapi.exception.CsvTableParsingException;
import ca.bc.gov.backendstartapi.vo.parser.CsvParsingResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

/**
 * Parser for tables in CSV files. A table is formed by two parts:
 *
 * <ol>
 *   <li>a header, which must be the first non-blank line on the file
 *   <li>a body, a set of rows with the same number of columns as there are headers
 * </ol>
 *
 * <p>Instances of this class are composed of two other parsers: {@link CsvTableHeaderParser one for
 * the table header} and {@link CsvTableRowParser one for the table rows}.
 *
 * @param <H> the {@link Enum} of the accepted headers
 * @param <R> the class into which the rows should be parsed
 */
@RequiredArgsConstructor
class CsvTableParser<H extends Enum<H> & CsvParsingHeader, R extends CsvParsingResult> {

  @NonNull private final CsvTableHeaderParser<H> headerParser;

  @NonNull private final CsvTableRowParser<H, R> rowParser;

  /**
   * Parse the content of {@code csvFile} into a list of objects.
   *
   * @param csvFile a text file with a CSV table
   * @return a list of the parsed data, in the order they appear
   * @throws IOException if something goes wrong while reading the file
   * @throws CsvTableParsingException if the file doesn't conform to the template rules
   */
  public List<R> parse(Resource csvFile) throws IOException {
    var fileReader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));
    final var headers = headerParser.parse(fileReader.readLine());

    var allHeaders = Arrays.asList(headerParser.getHeaderClass().getEnumConstants());
    if (!headers.containsAll(allHeaders)) {
      throw new CsvTableParsingException(
          "The CSV table is missing the following headers: "
              + allHeaders.stream()
                  .filter(not(headers::contains))
                  .map(H::toString)
                  .collect(Collectors.joining(", ")));
    }

    return fileReader
        .lines()
        .filter(not(String::isEmpty))
        .map(row -> rowParser.parse(row, headers))
        .toList();
  }
}
