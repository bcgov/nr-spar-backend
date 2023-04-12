package ca.bc.gov.backendstartapi.exception;

/**
 * Unchecked exception to be thrown when problems with CSV table parsing (mainly done inside {@link
 * ca.bc.gov.backendstartapi.service.parser}) occur.
 *
 * <p>Used mostly for wrapping other exceptions.
 */
public class CsvTableParsingException extends RuntimeException {

  public CsvTableParsingException(String reason) {
    super(reason);
  }

  public CsvTableParsingException(String reason, Throwable cause) {
    super(reason, cause);
  }
}
