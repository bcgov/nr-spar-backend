package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.enums.LatitudeCodeEnum;
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
