package ca.bc.gov.backendstartapi.service;

import static org.springframework.http.HttpHeaders.ACCEPT;

import ca.bc.gov.backendstartapi.dto.ForestClientDto;
import java.time.Duration;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/** Makes HTTP requests to the Forest Client API server. */
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class ForestClientApiProvider {

  private final RestTemplate restTemplate;

  @Autowired
  ForestClientApiProvider(
      @Value("${forest-client-api.address}") String forestClientApiAddress,
      @Value("${forest-client-api.key}") String forestClientApiKey,
      RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate =
        restTemplateBuilder
            .setConnectTimeout(Duration.ofSeconds(5))
            .setReadTimeout(Duration.ofSeconds(5))
            .rootUri(forestClientApiAddress)
            .defaultHeader("X-API-KEY", forestClientApiKey)
            .defaultHeader(ACCEPT, MediaType.APPLICATION_JSON.toString())
            .build();
  }

  /**
   * Fetch a forest client by its client number.
   *
   * @param number the client number to search for
   * @return the forest client with client number {@code number}, if one exists
   */
  public Optional<ForestClientDto> fetchByClientNumber(String number) {
    log.debug(String.format("Fetching client %s", number));
    var response =
        restTemplate.getForEntity("/clients/findByClientNumber/" + number, ForestClientDto.class);
    log.debug(String.format("Result for client %s: %s", number, response));
    return Optional.of(response.getBody());
  }
}
