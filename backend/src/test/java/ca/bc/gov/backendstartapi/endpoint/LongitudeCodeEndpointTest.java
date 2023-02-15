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
@WebMvcTest(LongitudeCodeEndpoint.class)
class LongitudeCodeEndpointTest {

  @Autowired private MockMvc mockMvc;

  @Test
  @DisplayName("getAllLongitudeCodes")
  @WithMockUser(roles = "user_read")
  void getAllLatitudeCodes() throws Exception {
    mockMvc
        .perform(
            get("/api/longitude-codes")
                .with(csrf().asHeader())
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$[0].code").value("E"))
        .andExpect(jsonPath("$[0].description").value("East"))
        .andExpect(jsonPath("$[1].code").value("W"))
        .andExpect(jsonPath("$[1].description").value("West"))
        .andReturn();
  }
}
