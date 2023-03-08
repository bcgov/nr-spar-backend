package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.dto.ForestClientDto;
import ca.bc.gov.backendstartapi.service.ForestClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

/** Controller for forest client-related endpoints. */
@RestController
@RequestMapping(path = "/api/forest-clients", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "ForestClient", description = "The many agencies that work with the ministry.")
public class ForestClientEndpoint {

  private final ForestClientService forestClientService;

  /**
   * Fetch a forest client by its client number.
   *
   * @param number the number that identifies the desired client
   * @return the forest client with client number {@code number}, if it exists
   */
  @GetMapping(path = "/{number}")
  @Operation(
      summary = "Fetch a forest client",
      description = "Returns the forest client identified by `number`, if there is one.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ForestClientDto.class))),
        @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
      })
  public ResponseEntity<Serializable> fetchClient(
      @PathVariable("number")
          @Pattern(regexp = "\\d{8}")
          @Parameter(
              name = "number",
              in = ParameterIn.PATH,
              description = "Number that identifies the client number to be fetched.")
          String number) {
    try {
      var response = forestClientService.fetchClient(number).map(Serializable.class::cast);
      return ResponseEntity.of(response);
    } catch (HttpStatusCodeException e) {
      log.warn("External error while retrieving forest client " + number, e);
      return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
    }
  }
}
