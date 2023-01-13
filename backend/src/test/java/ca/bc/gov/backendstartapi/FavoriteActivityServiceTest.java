package ca.bc.gov.backendstartapi.endpoint;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ca.bc.gov.backendstartapi.dto.FavoriteActivityDto;
import ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity;
import ca.bc.gov.backendstartapi.entity.UserEntity;
import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import ca.bc.gov.backendstartapi.repository.FavoriteActivityRepository;
import ca.bc.gov.backendstartapi.repository.UserRepository;
import ca.bc.gov.backendstartapi.service.FavoriteActivityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@TestInstance(Lifecycle.PER_CLASS)
class FavoriteActivityServiceTest {

  private MockMvc mockMvc;

  @Autowired private WebApplicationContext webApplicationContext;

  @MockBean UserRepository userRepository;

  @MockBean FavoriteActivityRepository favoriteActivityRepository;

  @BeforeEach
  public void setup() {
    this.mockMvc =
        MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
  }

  private String stringify(Object obj) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    return ow.writeValueAsString(obj);
  }

  @Test
  @DisplayName("createFavoriteActivitySuccessTest")
  @WithMockUser(roles = "user_write")
  void createFavoriteActivitySuccessTest() throws Exception {
    FavoriteActivityDto activityDto =
        new FavoriteActivityDto(ActivityEnum.SEEDLING_REQUEST.getTitle());

    UserEntity user = new UserEntity();
    user.setEmail("user@test.com");
    user.setId(1L);
    when(userRepository.findAllByEmail(any())).thenReturn(Collections.singletonList(user));

    FavoriteActivityEntity activity = new FavoriteActivityEntity();
    activity.setActivityTitle("Seedling request");
    activity.setHighlighted(Boolean.FALSE);
    activity.setEnabled(Boolean.TRUE);
    when(favoriteActivityRepository.save(any())).thenReturn(activity);

    mockMvc
        .perform(
            post("/api/favorite_activity")
                .with(csrf().asHeader())
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(stringify(activityDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.activityTitle").value("Seedling request"))
        .andExpect(jsonPath("$.highlighted").value("false"))
        .andExpect(jsonPath("$.enabled").value("true"))
        .andReturn();
  }

  @Test
  @DisplayName("createFavoriteActivityUserNotFoundTest")
  @WithMockUser(roles = "user_write")
  void createFavoriteActivityUserNotFoundTest() throws Exception {
    FavoriteActivityDto activityDto =
        new FavoriteActivityDto(ActivityEnum.SEEDLING_REQUEST.getTitle());

    when(userRepository.findAllByEmail(any())).thenReturn(Collections.emptyList());

    mockMvc
        .perform(
            post("/api/favorite_activity")
                .with(csrf().asHeader())
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(stringify(activityDto)))
        .andExpect(status().isNotFound())
        .andReturn();
  }

  @Test
  @DisplayName("createFavoriteActivityNotFoundTest")
  @WithMockUser(roles = "user_write")
  void createFavoriteActivityNotFoundTest() throws Exception {
    FavoriteActivityDto activityDto = new FavoriteActivityDto("Any Title Here");

    UserEntity user = new UserEntity();
    user.setEmail("user@test.com");
    user.setId(1L);
    when(userRepository.findAllByEmail(any())).thenReturn(Collections.singletonList(user));

    when(favoriteActivityRepository.save(any())).thenReturn(Optional.empty());

    mockMvc
        .perform(
            post("/api/favorite_activity")
                .with(csrf().asHeader())
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(stringify(activityDto)))
        .andExpect(status().isNotFound())
        .andReturn();
  }

  @Test
  @DisplayName("createFavoriteActivityDuplicatedTest")
  @WithMockUser(roles = "user_write")
  void createFavoriteActivityDuplicatedTest() throws Exception {
    FavoriteActivityDto activityDto =
        new FavoriteActivityDto(ActivityEnum.SEEDLING_REQUEST.getTitle());

    UserEntity user = new UserEntity();
    user.setEmail("user@test.com");
    user.setId(1L);
    when(userRepository.findAllByEmail(any())).thenReturn(Collections.singletonList(user));

    FavoriteActivityEntity activity = new FavoriteActivityEntity();
    activity.setActivityTitle("Seedling request");
    activity.setHighlighted(Boolean.FALSE);
    activity.setEnabled(Boolean.TRUE);
    when(favoriteActivityRepository.save(any())).thenReturn(activity);

    String contentString = stringify(activityDto);

    mockMvc
        .perform(
            post("/api/favorite_activity")
                .with(csrf().asHeader())
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(contentString))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.activityTitle").value("Seedling request"))
        .andExpect(jsonPath("$.highlighted").value("false"))
        .andExpect(jsonPath("$.enabled").value("true"))
        .andReturn();

    when(favoriteActivityRepository.findAllByUser(any()))
        .thenReturn(Collections.singletonList(activity));

    mockMvc
        .perform(
            post("/api/favorite_activity")
                .with(csrf().asHeader())
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(contentString))
        .andExpect(status().isBadRequest())
        .andReturn();
  }
}
