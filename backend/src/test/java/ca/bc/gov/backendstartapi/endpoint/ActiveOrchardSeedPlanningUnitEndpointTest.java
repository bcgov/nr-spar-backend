package ca.bc.gov.backendstartapi.endpoint;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ca.bc.gov.backendstartapi.entity.ActiveOrchardSeedPlanningUnit;
import ca.bc.gov.backendstartapi.repository.ActiveOrchardSeedPlanningUnitRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@WithMockUser(roles = "user_read")
class ActiveOrchardSeedPlanningUnitEndpointTest {

  @MockBean private ActiveOrchardSeedPlanningUnitRepository repository;

  private MockMvc mockMvc;

  private final WebApplicationContext webApplicationContext;

  ActiveOrchardSeedPlanningUnitEndpointTest(WebApplicationContext webApplicationContext) {
    this.webApplicationContext = webApplicationContext;
  }

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void testSearchActiveDefault() throws Exception {
    List<ActiveOrchardSeedPlanningUnit> actives =
        List.of(new ActiveOrchardSeedPlanningUnit("000", 1, true, false, false));
    List<ActiveOrchardSeedPlanningUnit> inactives =
        List.of(new ActiveOrchardSeedPlanningUnit("000", 2, false, false, false));
    given(repository.findByOrchardIdAndActive("000", true)).willReturn(actives);
    given(repository.findByOrchardIdAndActive("000", false)).willReturn(inactives);

    mockMvc
        .perform(get("/api/orchards/000/seed-plan-units").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpectAll(
            jsonPath("$[0].active").value("true"), jsonPath("$[0].seedPlanningUnitId").value("1"));
  }

  @Test
  void testSearchInactiveDefault() throws Exception {
    List<ActiveOrchardSeedPlanningUnit> actives =
        List.of(new ActiveOrchardSeedPlanningUnit("000", 1, true, false, false));
    List<ActiveOrchardSeedPlanningUnit> inactives =
        List.of(new ActiveOrchardSeedPlanningUnit("000", 2, false, false, false));
    given(repository.findByOrchardIdAndActive("000", true)).willReturn(actives);
    given(repository.findByOrchardIdAndActive("000", false)).willReturn(inactives);

    mockMvc
        .perform(
            get("/api/orchards/000/seed-plan-units?active=false")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpectAll(
            jsonPath("$[0].active").value("false"), jsonPath("$[0].seedPlanningUnitId").value("2"));
  }
}
