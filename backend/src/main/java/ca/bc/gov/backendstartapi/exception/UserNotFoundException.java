package ca.bc.gov.backendstartapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/** This class represents a user not found and will trigger a RuntimeException. */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ResponseStatusException {

  public UserNotFoundException() {
    super(HttpStatus.NOT_FOUND, "User not registered!");
  }
}
