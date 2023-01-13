package ca.bc.gov.backendstartapi.exception;

/** This class represents a situation where an activity is already registered to a user. */
public class FavoriteActivityExistsToUser extends RuntimeException {

  public FavoriteActivityExistsToUser() {
    super("Activity already registered to this user!");
  }
}
