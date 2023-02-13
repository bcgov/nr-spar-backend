package ca.bc.gov.backendstartapi.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import org.springframework.validation.FieldError;

/**
 * Used as error responses for REST endpoints when validation constraints (from {@link
 * jakarta.validation.Validation}) problems occur.
 *
 * <p>This class is to be constructed from {@link FieldError FieldErrors}.
 */
@Getter
@Schema(description = "An object containing the error message and the invalid fields")
public class ValidationExceptionResponse {

  private static final String MESSAGE_TEMPLATE = "%d field(s) with validation problems!";

  @Schema(description = "The error message")
  private final String errorMessage;

  @Schema(description = "An array of 'FieldIssue' with the invalid fields")
  private final List<FieldIssue> fields;

  /**
   * The sole constructor of this class.
   *
   * @param errors all the validation problems to be listed as a response
   */
  public ValidationExceptionResponse(List<FieldError> errors) {
    this.fields =
        errors.stream()
            .map(error -> new FieldIssue(error.getField(), error.getDefaultMessage()))
            .toList();
    this.errorMessage = String.format(MESSAGE_TEMPLATE, fields.size());
  }
}
