package ca.bc.gov.backendstartapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/** This class represents an invalid activity, that means an empty or null value. */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidActivityException extends ResponseStatusException {

  public InvalidActivityException() {
    super(HttpStatus.NOT_FOUND, "Invalid activity or page name!");
  }
}
