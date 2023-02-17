package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.dto.DescribedEnumDto;
import ca.bc.gov.backendstartapi.enums.DescribedEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * A blueprint of endpoints for fetching {@link DescribedEnum DescribedEnums}. Ideally, it'll have
 * all the endpoints needed for these resources and the developer only needs to implement a method
 * that returns the target class.
 *
 * @param <E> The {@link Enum} to be fetched by this controller. It must implement {@link
 *     DescribedEnum}
 */
interface DescribedEnumEndpoint<E extends Enum<E> & DescribedEnum> {

  /**
   * Get the class of enum to be fetched by the endpoints.
   *
   * @return the enum class this controller works with
   */
  Class<E> enumClass();

  /**
   * Fetch all the possible values of {@link E}.
   *
   * @return a list with all the possible values for {@code E} and their description
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A list of all the codes and their descriptions.")
      })
  default ResponseEntity<List<DescribedEnumDto<E>>> fetchAll() {
    var valueDtos =
        Arrays.stream(enumClass().getEnumConstants()).map(DescribedEnumDto::new).toList();
    return ResponseEntity.ok(valueDtos);
  }

  /**
   * Fetch a specific value of {@link E} and its description.
   *
   * @param code the name of the enum value to be fetched
   * @return the enum value named {@code code}, if it exists
   */
  @GetMapping(path = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      responses = {
        @ApiResponse(responseCode = "200", description = "The code that was found."),
        @ApiResponse(
            responseCode = "404",
            description = "No code was found",
            content = @Content(schema = @Schema(hidden = true)))
      })
  default ResponseEntity<DescribedEnumDto<E>> fetch(
      @Parameter(description = "The code to be fetched.") @PathVariable("code") String code) {
    var valueDto =
        Arrays.stream(enumClass().getEnumConstants())
            .dropWhile(v -> !v.name().equals(code))
            .findFirst()
            .map(DescribedEnumDto::new);
    return ResponseEntity.of(valueDto);
  }
}
