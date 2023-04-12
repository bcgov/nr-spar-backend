package ca.bc.gov.backendstartapi.service.parser;

import ca.bc.gov.backendstartapi.enums.parser.CsvParsingHeader;
import ca.bc.gov.backendstartapi.exception.CsvTableParsingException;
import ca.bc.gov.backendstartapi.vo.parser.CsvParsingResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Parser for rows of CSV tables.
 *
 * @param <H> the {@link Enum} of the valid headers
 * @param <R> the class to which the rows will be parsed
 */
@RequiredArgsConstructor
class CsvTableRowParser<H extends Enum<H> & CsvParsingHeader, R extends CsvParsingResult> {

  @NonNull private final Function<Map<H, Number>, R> create;

  /**
   * Parse a series of comma-separated values into instances of {@link R}.
   *
   * @param row the comma-separated values to be parsed
   * @param headers the headers that identify the parsed values
   * @return a list with the values parsed from {@code row}
   * @throws CsvTableParsingException if any value of {@code row} cannot be parsed into {@link R}
   */
  public R parse(String row, List<H> headers) {
    String[] cells = row.split(",", -1);

    if (cells.length > headers.size()) {
      throw new CsvTableParsingException(
          String.format(
              "There are more columns (%d) than headers (%d)", cells.length, headers.size()));
    } else if (cells.length < headers.size()) {
      throw new CsvTableParsingException(
          String.format(
              "There are more headers (%d) than columns (%d)", headers.size(), cells.length));
    }

    Map<H, Number> data = new HashMap<>();

    try {
      IntStream.range(0, headers.size())
          .forEach(
              i -> data.put(headers.get(i), Double.valueOf(cells[i].isBlank() ? "0" : cells[i])));
    } catch (NumberFormatException e) {
      throw new CsvTableParsingException(
          String.format("All values in the table must be numbers; line at fault: %s", row), e);
    }

    return create.apply(data);
  }
}
