package ca.bc.gov.backendstartapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import ca.bc.gov.backendstartapi.dto.ForestClientDto;
import ca.bc.gov.backendstartapi.enums.ForestClientStatusEnum;
import ca.bc.gov.backendstartapi.enums.ForestClientTypeEnum;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ForestClientServiceTest {

  @MockBean private RestTemplate restTemplate;

  private ForestClientService forestClientService;

  @BeforeEach
  void setup() {
    var provider = new ForestClientApiProvider(restTemplate);
    forestClientService = new ForestClientService(provider);
  }

  @Test
  void fetchExistentClient() {
    var client =
        new ForestClientDto(
            "00000000",
            "SMITH",
            "JOHN",
            "JOSEPH",
            ForestClientStatusEnum.ACT,
            ForestClientTypeEnum.I,
            "JSMITH");

    given(restTemplate.getForEntity("/clients/findByClientNumber/00000000", ForestClientDto.class))
        .willReturn(ResponseEntity.ok(client));

    var fetchResult = forestClientService.fetchClient("00000000");

    assertTrue(fetchResult.isPresent());
    assertEquals(client, fetchResult.get());
  }

  @Test
  void fetchInexistentClient() {
    var client =
        new ForestClientDto(
            "00000000",
            "SMITH",
            "JOHN",
            "JOSEPH",
            ForestClientStatusEnum.ACT,
            ForestClientTypeEnum.I,
            "JSMITH");

    given(restTemplate.getForEntity("/clients/findByClientNumber/00000000", ForestClientDto.class))
        .willReturn(ResponseEntity.ok(client));
    given(restTemplate.getForEntity("/clients/findByClientNumber/00000001", ForestClientDto.class))
        .willThrow(
            HttpClientErrorException.create(
                HttpStatus.NOT_FOUND,
                "Not Found",
                HttpHeaders.EMPTY,
                "Client 00000001 not found.".getBytes(StandardCharsets.UTF_8),
                null));

    var fetchResult = forestClientService.fetchClient("00000001");

    assertTrue(fetchResult.isEmpty());
  }
}
