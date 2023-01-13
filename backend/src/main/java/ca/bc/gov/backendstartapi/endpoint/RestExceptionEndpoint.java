package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.exception.FavoriteActivityExistsToUser;
import ca.bc.gov.backendstartapi.exception.ActivityNotFoundException;
import ca.bc.gov.backendstartapi.exception.NotRemovableEntityException;
import ca.bc.gov.backendstartapi.exception.UserNotFoundException;
import ca.bc.gov.backendstartapi.response.ExceptionResponse;
import ca.bc.gov.backendstartapi.response.ValidationExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** This class is responsible for handling all kind of exceptions and validations. */
@RestControllerAdvice
public class RestExceptionEndpoint {

  /**
   * Handle all jakarta validation exceptions.
   *
   * @param ex MethodArgumentNotValidException instance
   * @return a Map of String containing all the invalid fields and messages
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ValidationExceptionResponse> validationException(
      MethodArgumentNotValidException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ValidationExceptionResponse(ex.getFieldErrors()));
  }

  /**
   * Handle a user not found exception.
   *
   * @param ex UserNotFoundException instance
   * @return a JSON message
   */
  @ExceptionHandler(UserNotFoundException.class)
  ResponseEntity<ExceptionResponse> userNotFound(UserNotFoundException ex) {
    ExceptionResponse exResponse = new ExceptionResponse(ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exResponse);
  }

  /**
   * Handle a removal attempt of an entity that can't be removed.
   *
   * @param ex NotRemovableEntityException instance
   * @return a JSON message
   */
  @ExceptionHandler(NotRemovableEntityException.class)
  ResponseEntity<ExceptionResponse> notRemovableEntity(NotRemovableEntityException ex) {
    ExceptionResponse exResponse = new ExceptionResponse(ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exResponse);
  }

  /**
   * Handle a user not found exception.
   *
   * @param ex ActivityNotFoundException instance
   * @return a JSON message
   */
  @ExceptionHandler(ActivityNotFoundException.class)
  ResponseEntity<ExceptionResponse> activityNotFound(ActivityNotFoundException ex) {
    ExceptionResponse exResponse = new ExceptionResponse(ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exResponse);
  }

  /**
   * Handle a user not found exception.
   *
   * @param ex FavoriteActivityExistsToUser instance
   * @return a JSON message
   */
  @ExceptionHandler(FavoriteActivityExistsToUser.class)
  ResponseEntity<ExceptionResponse> activityExistsToUser(FavoriteActivityExistsToUser ex) {
    ExceptionResponse exResponse = new ExceptionResponse(ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exResponse);
  }
}
