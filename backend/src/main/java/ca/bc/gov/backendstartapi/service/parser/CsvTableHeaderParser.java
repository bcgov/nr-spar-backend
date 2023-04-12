package ca.bc.gov.backendstartapi.service.parser;

import ca.bc.gov.backendstartapi.enums.parser.CsvParsingHeader;
import ca.bc.gov.backendstartapi.exception.CsvTableParsingException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A parser for the header of a CSV table.
 *
 * <p>Must work for all instances of {@link CsvParsingHeader}.
 *
 * @param <H> the {@link Enum} of the possible headers
 */
@RequiredArgsConstructor
class CsvTableHeaderParser<H extends Enum<H> & CsvParsingHeader> {

  @NonNull @Getter private final Class<H> headerClass;

  /**
   * Parse a line of comma-separated values into elements of {@link H}.
   *
   * @param tableHeader a line of comma-separated values to be parsed
   * @return a list with the parsed values in the order they appear
   * @throws CsvTableParsingException if any of the values in {@code tableHeader} cannot be parsed
   *     into {@link H}
   */
  public List<H> parse(String tableHeader) {
    return Arrays.stream(tableHeader.split(",", -1))
        .peek(
            h -> {
              if (h.isBlank()) {
                throw new CsvTableParsingException("No empty cells allowed in the table header");
              }
            })
        .map(this::parseHeader)
        .toList();
  }

  /**
   * Parse a single header into an element of {@link H}.
   *
   * @param header the header value
   * @return the value of {@code H} that {@code header} maps to
   * @throws CsvTableParsingException if {@code header} maps to no element of {@code H}
   */
  private H parseHeader(String header) {
    try {
      return Arrays.stream(headerClass.getEnumConstants())
          .filter(h -> h.toString().equalsIgnoreCase(header))
          .findFirst()
          .orElseThrow();
    } catch (NoSuchElementException e) {
      throw new CsvTableParsingException(
          String.format(
              "%s could not be parsed as a header. Valid values are %s",
              header,
              Arrays.stream(headerClass.getEnumConstants())
                  .map(H::toString)
                  .collect(Collectors.joining(","))),
          e);
    }
  }
}
