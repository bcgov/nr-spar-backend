package ca.bc.gov.backendstartapi.endpoint;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ca.bc.gov.backendstartapi.entity.FavouriteActivityEntity;
import ca.bc.gov.backendstartapi.exception.FavoriteActivityExistsToUser;
import ca.bc.gov.backendstartapi.exception.InvalidActivityException;
import ca.bc.gov.backendstartapi.service.FavouriteActivityService;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FavouriteActivityEndpoint.class)
class FavouriteActivityEndpointTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private FavouriteActivityService favouriteActivityService;

  private static final String API_PATH = "/api/favourite-activities";

  private static final String CONTENT_HEADER = "Content-Type";

  private static final String JSON = "application/json";

  private String stringifyCreate(String activity) {
    StringBuilder json = new StringBuilder("{");
    if (!Objects.isNull(activity)) {
      json.append("\"activity\":\"").append(activity).append("\"");
    }
    json.append("}");
    return json.toString();
  }

  private FavouriteActivityEntity createEntity(String activity) {
    FavouriteActivityEntity activityEntity = new FavouriteActivityEntity();
    activityEntity.setActivity(activity);
    activityEntity.setHighlighted(Boolean.FALSE);
    activityEntity.setEnabled(Boolean.TRUE);
    return activityEntity;
  }

  @Test
  @DisplayName("createFavoriteActivitySuccessTest")
  @WithMockUser(roles = "user_write")
  void createFavoriteActivitySuccessTest() throws Exception {
    FavouriteActivityEntity activityEntity = createEntity("CREATE_A_CLASS_SEEDLOT");
    when(favouriteActivityService.createUserActivity(any())).thenReturn(activityEntity);

    mockMvc
        .perform(
            post(API_PATH)
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(stringifyCreate("CREATE_A_CLASS_SEEDLOT")))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.activity").value("CREATE_A_CLASS_SEEDLOT"))
        .andExpect(jsonPath("$.highlighted").value("false"))
        .andExpect(jsonPath("$.enabled").value("true"))
        .andReturn();
  }

  @Test
  @DisplayName("createFavoriteActivityNotFoundTest")
  @WithMockUser(roles = "user_write")
  void createFavoriteActivityNotFoundTest() throws Exception {
    when(favouriteActivityService.createUserActivity(any()))
        .thenThrow(new InvalidActivityException());

    mockMvc
        .perform(
            post(API_PATH)
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(stringifyCreate(null)))
        .andExpect(status().isBadRequest())
        .andReturn();
  }

  @Test
  @DisplayName("createFavoriteActivityDuplicatedTest")
  @WithMockUser(roles = "user_write")
  void createFavoriteActivityDuplicatedTest() throws Exception {
    String contentString = stringifyCreate("CREATE_A_CLASS_SEEDLOT");
    FavouriteActivityEntity activityEntity = createEntity("CREATE_A_CLASS_SEEDLOT");
    when(favouriteActivityService.createUserActivity(any())).thenReturn(activityEntity);

    mockMvc
        .perform(
            post(API_PATH)
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(contentString))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.activity").value("CREATE_A_CLASS_SEEDLOT"))
        .andExpect(jsonPath("$.highlighted").value("false"))
        .andExpect(jsonPath("$.enabled").value("true"))
        .andReturn();

    when(favouriteActivityService.createUserActivity(any()))
        .thenThrow(new FavoriteActivityExistsToUser());

    mockMvc
        .perform(
            post(API_PATH)
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(contentString))
        .andExpect(status().isBadRequest())
        .andReturn();
  }

  @Test
  @DisplayName("getAllUsersActivityTest")
  @WithMockUser(roles = "user_write")
  void getAllUsersActivityTest() throws Exception {

    FavouriteActivityEntity activityEntityOne = createEntity("CREATE_A_CLASS_SEEDLOT");
    FavouriteActivityEntity activityEntityTwo = createEntity("SEEDLOT_REGISTRATION");
    activityEntityTwo.setHighlighted(Boolean.TRUE);
    List<FavouriteActivityEntity> favoriteActivityEntities =
        List.of(activityEntityOne, activityEntityTwo);
    when(favouriteActivityService.getAllUserFavoriteActivities())
        .thenReturn(favoriteActivityEntities);
    mockMvc
        .perform(
            get(API_PATH)
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].activity").value("CREATE_A_CLASS_SEEDLOT"))
        .andExpect(jsonPath("$[0].highlighted").value("false"))
        .andExpect(jsonPath("$[0].enabled").value("true"))
        .andExpect(jsonPath("$[1].activity").value("SEEDLOT_REGISTRATION"))
        .andExpect(jsonPath("$[1].highlighted").value("true"))
        .andExpect(jsonPath("$[1].enabled").value("true"))
        .andReturn();
  }

  @Test
  @DisplayName("updateUserFavoriteActivity")
  @WithMockUser(roles = "user_write")
  void updateUserFavoriteActivity() throws Exception {
    FavouriteActivityEntity activityEntity = createEntity("EXISTING_SEEDLOTS");
    activityEntity.setId(10000L);
    when(favouriteActivityService.createUserActivity(any())).thenReturn(activityEntity);

    mockMvc
        .perform(
            post(API_PATH)
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(stringifyCreate("EXISTING_SEEDLOTS")))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.activity").value("EXISTING_SEEDLOTS"))
        .andExpect(jsonPath("$.highlighted").value("false"))
        .andExpect(jsonPath("$.enabled").value("true"))
        .andReturn();

    FavouriteActivityEntity activityUpdated = activityEntity.withHighlighted(true);

    when(favouriteActivityService.updateUserActivity(any(), any())).thenReturn(activityUpdated);

    String stringifyUpdate = "{\"highlighted\":\"true\",\"enabled\":\"true\"}";

    mockMvc
        .perform(
            put(API_PATH + "/{id}", activityEntity.getId())
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(stringifyUpdate))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.activity").value("EXISTING_SEEDLOTS"))
        .andExpect(jsonPath("$.highlighted").value("true"))
        .andExpect(jsonPath("$.enabled").value("true"))
        .andReturn();
  }

  @Test
  @DisplayName("deleteUserFavoriteActivity")
  @WithMockUser(roles = "user_write")
  void deleteUserFavoriteActivity() throws Exception {
    FavouriteActivityEntity activityEntity = createEntity("EXISTING_SEEDLOTS");
    activityEntity.setId(10000L);

    when(favouriteActivityService.createUserActivity(any())).thenReturn(activityEntity);

    mockMvc
        .perform(
            post(API_PATH)
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(stringifyCreate("EXISTING_SEEDLOTS")))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.activity").value("EXISTING_SEEDLOTS"))
        .andExpect(jsonPath("$.highlighted").value("false"))
        .andExpect(jsonPath("$.enabled").value("true"))
        .andReturn();

    FavouriteActivityEntity activityUpdated = activityEntity.withHighlighted(true);

    doNothing().when(favouriteActivityService).deleteUserActivity(any());

    mockMvc
        .perform(
            delete(API_PATH + "/{id}", activityUpdated.getId())
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }
}
