package ca.bc.gov.backendstartapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/** This class represents a not found activity from ActivityEnum. */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ActivityNotFoundException extends ResponseStatusException {

  public ActivityNotFoundException() {
    super(HttpStatus.NOT_FOUND, "Activity don't exist!");
  }
}
