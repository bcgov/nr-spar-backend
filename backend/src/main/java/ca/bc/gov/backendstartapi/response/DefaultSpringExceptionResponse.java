package ca.bc.gov.backendstartapi.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a validation object with detailed information in case of errors when receiving
 * requests.
 */
@Getter
@Setter
@Schema(description = "Represents a general error object")
public class DefaultSpringExceptionResponse {

  @Schema(
      description = "Date and time that the error occurred",
      type = "string",
      format = "date-time",
      example = "2023-02-10T18:59:10.538+00:00")
  private String timestamp;

  @Schema(description = "HTTP status code", example = "400")
  private Integer status;

  @Schema(description = "Short error title", example = "Bad Request")
  private String error;

  @Schema(
      description = "Message containing the full description of the error",
      example =
          """
          JSON parse error: Cannot deserialize value of type
          'ca.bc.gov.backendstartapi.enums.SampleEnum' from String 'any text here':
          not one of the values accepted for Enum class:
          [Sample one, Sample two, Sample three]
          """)
  private String message;

  @Schema(description = "Path of the called API", example = "/api/favourite-activities")
  private String path;
}
