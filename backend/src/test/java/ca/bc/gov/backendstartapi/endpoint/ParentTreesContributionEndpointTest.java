package ca.bc.gov.backendstartapi.endpoint;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ca.bc.gov.backendstartapi.exception.CsvTableParsingException;
import ca.bc.gov.backendstartapi.service.parser.SmpCalculationCsvTableParser;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@WithMockUser(roles = "user_read")
class ParentTreesContributionEndpointTest {

  private final ClassLoader classLoader = getClass().getClassLoader();

  @MockBean private SmpCalculationCsvTableParser smpCalculationCsvTableParser;

  private MockMvc mockMvc;

  private final WebApplicationContext webApplicationContext;

  ParentTreesContributionEndpointTest(WebApplicationContext webApplicationContext) {
    this.webApplicationContext = webApplicationContext;
  }

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void uploadSmpMixTable() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.multipart(
                    "/api/seedlots/00000/parent-trees-contribution/smp-calculation-table/upload")
                .file(
                    new MockMultipartFile(
                        "file",
                        "table.csv",
                        null,
                        Objects.requireNonNull(
                            classLoader.getResourceAsStream("csv/smpmix/finalEmptyLine.csv"))))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.MULTIPART_FORM_DATA))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void uploadConeAndPollenCountTable() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.multipart(
                    "/api/seedlots/00000/parent-trees-contribution/cone-pollen-count-table/upload")
                .file(
                    new MockMultipartFile(
                        "file",
                        "table.csv",
                        null,
                        Objects.requireNonNull(
                            classLoader.getResourceAsStream(
                                "csv/contribution/finalEmptyLine.csv"))))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.MULTIPART_FORM_DATA))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void uploadSmpMixTableWrongExtension() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.multipart(
                    "/api/seedlots/00000/parent-trees-contribution/smp-calculation-table/upload")
                .file(
                    new MockMultipartFile(
                        "file",
                        "table.txt",
                        null,
                        Objects.requireNonNull(
                            classLoader.getResourceAsStream("csv/smpmix/finalEmptyLine.csv"))))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.MULTIPART_FORM_DATA))
        .andExpect(status().isBadRequest())
        .andExpect(status().reason("CSV files only"));
  }

  @Test
  void uploadConeAndPollenCountTableWrongExtension() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.multipart(
                    "/api/seedlots/00000/parent-trees-contribution/cone-pollen-count-table/upload")
                .file(
                    new MockMultipartFile(
                        "file",
                        "table.txt",
                        null,
                        Objects.requireNonNull(
                            classLoader.getResourceAsStream(
                                "csv/contribution/finalEmptyLine.csv"))))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.MULTIPART_FORM_DATA))
        .andExpect(status().isBadRequest())
        .andExpect(status().reason("CSV files only"));
  }

  @Test
  void badRequestForCsvTableParsingException() throws Exception {
    var file = new MockMultipartFile("file", "meh.csv", null, "bad content".getBytes());
    final var errorReason = "reason";
    given(smpCalculationCsvTableParser.parse(file.getResource()))
        .willThrow(new CsvTableParsingException(errorReason));

    mockMvc
        .perform(
            MockMvcRequestBuilders.multipart(
                    "/api/seedlots/00000/parent-trees-contribution/smp-calculation-table/upload")
                .file(file)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.MULTIPART_FORM_DATA))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(status().reason(errorReason));
  }
}
