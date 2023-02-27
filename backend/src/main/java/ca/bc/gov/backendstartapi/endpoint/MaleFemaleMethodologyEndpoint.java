package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.enums.MaleFemaleMethodologyEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Endpoints to fetch {@link MaleFemaleMethodologyEnum}. */
@RestController
@RequestMapping(
    path = "/api/male-female-methodologies",
    produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@Tag(name = "MaleFemaleMethodology")
public class MaleFemaleMethodologyEndpoint
    implements DescribedEnumEndpoint<MaleFemaleMethodologyEnum> {

  @Override
  public Class<MaleFemaleMethodologyEnum> enumClass() {
    return MaleFemaleMethodologyEnum.class;
  }
}
