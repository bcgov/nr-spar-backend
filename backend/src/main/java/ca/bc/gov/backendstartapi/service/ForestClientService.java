package ca.bc.gov.backendstartapi.service;

import ca.bc.gov.backendstartapi.dto.ForestClientDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

/** Service to deal with {@link ForestClientDto ForestClients}. */
@Service
@RequiredArgsConstructor
@Slf4j
public class ForestClientService {

  private final ForestClientApiProvider provider;

  /**
   * Fetch a forest client by its client number.
   *
   * @param number the client number to search for
   * @return the forest client with client number {@code number}, if one exists
   */
  public Optional<ForestClientDto> fetchClient(String number) {
    try {
      return provider.fetchByClientNumber(number);
    } catch (HttpClientErrorException.NotFound e) {
      log.info(String.format("Client %s not found", number), e);
      return Optional.empty();
    }
  }
}
