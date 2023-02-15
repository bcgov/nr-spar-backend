package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.enums.SeedlotStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Endpoints to fetch {@link SeedlotStatus}. */
@RestController
@RequestMapping(path = "/api/seedlot-status", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@Tag(name = "SeedlotStatus")
public class SeedlotStatusEndpoint implements DescribedEnumEndpoint<SeedlotStatus> {

  @Override
  public Class<SeedlotStatus> enumClass() {
    return SeedlotStatus.class;
  }
}
