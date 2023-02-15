package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.enums.ConeCollectionMethodEnum;
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

/** This class exposes resources to handle all cone collection method codes. */
@RestController
@RequestMapping("/api/cone-collection-methods")
@Tag(
    name = "ConeCollectionMethodEndpoint",
    description = "Resources to handle all cone collection method codes")
public class ConeCollectionMethodEndpoint {

  /**
   * Get all cone collection method codes.
   *
   * @return A list of {@link ConeCollectionMethodEnum}
   */
  @GetMapping(produces = "application/json")
  @PreAuthorize("hasRole('user_read')")
  @Operation(
      summary = "Retrieves all cone collection method codes",
      description = "Returns a list containing all cone collection method codes.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "An array of objects containing code and description for each value.",
            content =
                @Content(
                    array =
                        @ArraySchema(
                            schema = @Schema(implementation = ConeCollectionMethodEnum.class)),
                    mediaType = "application/json")),
        @ApiResponse(
            responseCode = "401",
            description = "Access token is missing or invalid",
            content = @Content(schema = @Schema(implementation = Void.class)))
      })
  public List<ConeCollectionMethodEnum> getAllConeCollectionMethods() {
    return List.of(ConeCollectionMethodEnum.values());
  }
}
