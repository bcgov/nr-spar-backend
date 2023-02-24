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
@WebMvcTest(ConeCollectionMethodEndpoint.class)
class ConeCollectionMethodEndpointTest {

  @Autowired private MockMvc mockMvc;

  @Test
  @DisplayName("getAllConeCollectionMethodsTest")
  @WithMockUser(roles = "user_read")
  void getAllConeCollectionMethodsTest() throws Exception {
    mockMvc
        .perform(
            get("/api/cone-collection-methods")
                .with(csrf().asHeader())
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$[0].code").value("01"))
        .andExpect(jsonPath("$[0].description").value("Aerial raking"))
        .andExpect(jsonPath("$[1].code").value("02"))
        .andExpect(jsonPath("$[1].description").value("Aerial clipping/topping"))
        .andExpect(jsonPath("$[2].code").value("03"))
        .andExpect(jsonPath("$[2].description").value("Felled trees"))
        .andExpect(jsonPath("$[3].code").value("04"))
        .andExpect(jsonPath("$[3].description").value("Climbing"))
        .andExpect(jsonPath("$[4].code").value("05"))
        .andExpect(jsonPath("$[4].description").value("Squirrel cache"))
        .andExpect(jsonPath("$[5].code").value("06"))
        .andExpect(jsonPath("$[5].description").value("Ground, Ladder and/or Hydraulic Lift"))
        .andExpect(jsonPath("$[6].code").value("07"))
        .andExpect(jsonPath("$[6].description").value("Unknown"))
        .andExpect(jsonPath("$[7].code").value("08"))
        .andExpect(jsonPath("$[7].description").value("Squirrel harvesting/dropping"))
        .andReturn();
  }
}
