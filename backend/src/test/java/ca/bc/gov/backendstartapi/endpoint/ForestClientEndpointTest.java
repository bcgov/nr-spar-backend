package ca.bc.gov.backendstartapi.endpoint;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ca.bc.gov.backendstartapi.dto.ForestClientDto;
import ca.bc.gov.backendstartapi.enums.ForestClientStatusEnum;
import ca.bc.gov.backendstartapi.enums.ForestClientTypeEnum;
import ca.bc.gov.backendstartapi.service.ForestClientService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ForestClientEndpointTest {

  private MockMvc mockMvc;

  private final WebApplicationContext webApplicationContext;

  @MockBean private ForestClientService forestClientService;

  ForestClientEndpointTest(WebApplicationContext webApplicationContext) {
    this.webApplicationContext = webApplicationContext;
  }

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void fetchExistentClient() throws Exception {
    var client =
        new ForestClientDto(
            "00000000",
            "SMITH",
            "JOHN",
            "JOSEPH",
            ForestClientStatusEnum.ACT,
            ForestClientTypeEnum.I,
            "JSMITH");

    given(forestClientService.fetchClient("00000000")).willReturn(Optional.of(client));

    mockMvc
        .perform(get("/api/forest-clients/00000000").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpectAll(
            jsonPath("$.clientNumber").value(client.clientNumber()),
            jsonPath("$.clientName").value(client.clientName()),
            jsonPath("$.legalFirstName").value(client.legalFirstName()),
            jsonPath("$.legalMiddleName").value(client.legalMiddleName()),
            jsonPath("$.clientStatusCode").value(client.clientStatusCode().name()),
            jsonPath("$.clientTypeCode").value(client.clientTypeCode().name()),
            jsonPath("$.clientAcronym").value(client.clientAcronym()));
  }

  @Test
  void fetchNonExistentClient() throws Exception {
    given(forestClientService.fetchClient("00000000")).willReturn(Optional.empty());

    mockMvc
        .perform(get("/api/forest-clients/00000000").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
