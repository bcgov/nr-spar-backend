package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.entity.ActiveOrchardSeedPlanUnit;
import ca.bc.gov.backendstartapi.repository.ActiveOrchardSeedPlanUnitRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Rest controller to fetch relations between orchards and Seed Plan Units (SPU). */
@RestController
@RequestMapping(path = "/api/orchards", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@Tag(name = "Orchard")
@RequiredArgsConstructor
public class ActiveOrchardSeedPlanUnitEndpoint {

  private final ActiveOrchardSeedPlanUnitRepository repository;

  @Operation(
      operationId = "findSpuByOrchard",
      summary = "Find associations between seed plan units and an orchard",
      description =
          "Find the associations of seed plan units and the orchard identified by `orchardId`.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A list of the associations between the orchard and seed plan units.")
      })
  @GetMapping(path = "/{orchardId}/seed-plan-units")
  @PreAuthorize("hasRole('user_read')")
  public List<ActiveOrchardSeedPlanUnit> findByOrchard(
      @Parameter(description = "The identifier of an orchard") @PathVariable(name = "orchardId")
          String orchardId,
      @Parameter(description = "If the association must be active or not")
          @RequestParam(name = "active", defaultValue = "true")
          boolean active) {
    return repository.findByOrchardIdAndActive(orchardId, active);
  }
}
