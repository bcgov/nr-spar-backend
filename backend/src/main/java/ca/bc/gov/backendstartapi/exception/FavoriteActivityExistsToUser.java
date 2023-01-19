package ca.bc.gov.backendstartapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/** This class represents a situation where an activity is already registered to a user. */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FavoriteActivityExistsToUser extends ResponseStatusException {

  public FavoriteActivityExistsToUser() {
    super(HttpStatus.BAD_REQUEST, "Activity already registered to this user!");
  }
}
