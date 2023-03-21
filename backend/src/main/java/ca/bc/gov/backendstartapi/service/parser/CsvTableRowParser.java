package ca.bc.gov.backendstartapi.service.parser;

import ca.bc.gov.backendstartapi.enums.parser.CsvParsingHeader;
import ca.bc.gov.backendstartapi.exception.CsvTableParsingException;
import ca.bc.gov.backendstartapi.vo.parser.CsvParsingResult;
import java.util.Arrays;
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
    String[] cells = fillCells(row.split(","), headers.size());

    Map<H, Number> data = new HashMap<>();

    try {
      IntStream.range(0, headers.size())
          .forEach(
              i -> data.put(headers.get(i), Double.valueOf(cells[i].isBlank() ? "0" : cells[i])));
    } catch (NumberFormatException e) {
      throw new CsvTableParsingException("All values in the table must be numbers", e);
    }

    return create.apply(data);
  }

  /**
   * Make sure we have the right amount cells: if we have fewer cells then we should have, pad it
   * with blank strings at the end of the array.
   *
   * @param cells an array with the value of each cell
   * @param columns the number of columns we should have
   * @return an array with the same values as {@code cell}, padded with blank strings at its end, if
   *     necessary
   * @throws CsvTableParsingException if {@code cells}' length is greater than {@code columns}
   */
  private String[] fillCells(String[] cells, int columns) {
    if (cells.length > columns) {
      throw new CsvTableParsingException(
          String.format("There are more columns (%d) than headers (%d)", cells.length, columns));
    }
    if (cells.length < columns) {
      var newCells = Arrays.copyOf(cells, columns);
      Arrays.fill(newCells, cells.length, columns, "");
      return newCells;
    }
    return cells;
  }
}
