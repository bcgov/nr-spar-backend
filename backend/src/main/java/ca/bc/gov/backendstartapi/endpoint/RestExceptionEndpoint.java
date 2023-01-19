package ca.bc.gov.backendstartapi.endpoint;

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
}
