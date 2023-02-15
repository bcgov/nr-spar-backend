package ca.bc.gov.backendstartapi.endpoint;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GeneticClassEndpoint.class)
class GeneticClassEndpointTest {

  @Autowired private MockMvc mockMvc;

  @Test
  @DisplayName("getAllGeneticClassesTest")
  @WithMockUser(roles = "user_read")
  void getAllGeneticClassesTest() throws Exception {
    mockMvc
        .perform(
            get("/api/genetic-classes")
                .with(csrf().asHeader())
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$[0].code").value("A"))
        .andExpect(jsonPath("$[0].description").value("Orchard Seed or Cuttings"))
        .andExpect(jsonPath("$[1].code").value("B"))
        .andExpect(jsonPath("$[1].description").value("Natural Stand Seed or Cuttings"))
        .andReturn();
  }
}
