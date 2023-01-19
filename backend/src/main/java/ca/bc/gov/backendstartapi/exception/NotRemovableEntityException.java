package ca.bc.gov.backendstartapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/** This exception represents an entity that shouldn't or can't be deleted. */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotRemovableEntityException extends ResponseStatusException {

  /** Creates an NotRemovableEntityException instance. */
  public NotRemovableEntityException() {
    super(HttpStatus.BAD_REQUEST, "Entity not removable!");
  }
}
