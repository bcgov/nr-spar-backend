package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.enums.GeneticClassEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** This class exposes resources to handle all genetic class codes. */
@RestController
@RequestMapping(path = "/api/genetic-classes", produces = "application/json")
@Tag(name = "GeneticClass")
public class GeneticClassEndpoint implements DescribedEnumEndpoint<GeneticClassEnum> {

  @Override
  public Class<GeneticClassEnum> enumClass() {
    return GeneticClassEnum.class;
  }
}
