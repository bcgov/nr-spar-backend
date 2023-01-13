package ca.bc.gov.backendstartapi.exception;

/** This class represents a not found activity from ActivityEnum. */
public class ActivityNotFoundException extends RuntimeException {

  public ActivityNotFoundException() {
    super("Activity don't exist!");
  }
}
