package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.vo.CheckVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** This class represents a check endpoint object. */
@Slf4j
@RestController
public class CheckEndpoint {

  @Value("${nr-spar-backend-version}")
  private String nrSparBackendVersion;

  /**
   * Check if the service is up and running.
   *
   * @return a CheckVo object containing a message and a release version
   */
  @GetMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
  public CheckVo check() {
    return CheckVo.builder().message("OK").release(nrSparBackendVersion).build();
  }
}
