package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.enums.LongitudeCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** This class exposes resources to handle all longitude codes. */
@RestController
@RequestMapping("/api/longitude-codes")
@Tag(name = "CollectionLongitudeEndpoint", description = "Resources to handle all longitude codes")
public class LongitudeCodeEndpoint {

  /**
   * Get all longitude codes.
   *
   * @return A list of {@link LongitudeCodeEnum}
   */
  @GetMapping(produces = "application/json")
  @PreAuthorize("hasRole('user_read')")
  @Operation(
      summary = "Retrieves all longitude codes",
      description = "Returns a list containing all longitude codes.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "An array of objects containing code and description for each value.",
            content =
                @Content(
                    array =
                        @ArraySchema(schema = @Schema(implementation = LongitudeCodeEnum.class)),
                    mediaType = "application/json")),
        @ApiResponse(
            responseCode = "401",
            description = "Access token is missing or invalid",
            content = @Content(schema = @Schema(implementation = Void.class)))
      })
  public List<LongitudeCodeEnum> getAllCollectionLongitudes() {
    return List.of(LongitudeCodeEnum.values());
  }
}
