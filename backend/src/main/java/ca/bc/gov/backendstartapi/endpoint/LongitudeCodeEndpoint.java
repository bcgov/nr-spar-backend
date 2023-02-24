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
@RequestMapping(path = "/api/longitude-codes", produces = "application/json")
@Tag(name = "LongitudeCode", description = "Resources to handle all longitude codes")
public class LongitudeCodeEndpoint implements DescribedEnumEndpoint<LongitudeCodeEnum> {

  @Override
  public Class<LongitudeCodeEnum> enumClass() {
    return LongitudeCodeEnum.class;
  }
}
