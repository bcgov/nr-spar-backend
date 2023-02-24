package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.enums.SeedlotSourceEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Endpoints to fetch {@link SeedlotSourceEnum}. */
@RestController
@RequestMapping(path = "/api/seedlot-sources", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@Tag(name = "SeedlotSource")
public class SeedlotSourceEndpoint implements DescribedEnumEndpoint<SeedlotSourceEnum> {

  @Override
  public Class<SeedlotSourceEnum> enumClass() {
    return SeedlotSourceEnum.class;
  }
}
