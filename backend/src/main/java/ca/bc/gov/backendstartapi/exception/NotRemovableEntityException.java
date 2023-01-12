package ca.bc.gov.backendstartapi.exception;

/** This exception represents an entity that shouldn't or can't be deleted. */
public class NotRemovableEntityException extends RuntimeException {

  /** Creates an NotRemovableEntityException instance. */
  public NotRemovableEntityException() {
    super("Entity not removable!");
  }
}
