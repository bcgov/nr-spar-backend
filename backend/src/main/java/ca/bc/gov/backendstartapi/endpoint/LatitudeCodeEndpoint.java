package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.enums.LatitudeCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** This class exposes resources to handle all latitude codes. */
@RestController
@RequestMapping(path = "/api/latitude-codes", produces = "application/json")
@Tag(name = "LatitudeCode", description = "Resources to handle all latitude codes")
public class LatitudeCodeEndpoint implements DescribedEnumEndpoint<LatitudeCodeEnum> {

  @Override
  public Class<LatitudeCodeEnum> enumClass() {
    return LatitudeCodeEnum.class;
  }
}
