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

import ca.bc.gov.backendstartapi.dto.FavoriteActivityCreateDto;
import ca.bc.gov.backendstartapi.dto.FavoriteActivityUpdateDto;
import ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity;
import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import ca.bc.gov.backendstartapi.exception.ActivityNotFoundException;
import ca.bc.gov.backendstartapi.exception.FavoriteActivityExistsToUser;
import ca.bc.gov.backendstartapi.exception.UserNotFoundException;
import ca.bc.gov.backendstartapi.service.FavoriteActivityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.List;
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
@WebMvcTest(FavoriteActivityEndpoint.class)
class FavoriteActivityEndpointTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private FavoriteActivityService favoriteActivityService;

  private static final String API_PATH = "/api/favorite-activities";

  private static final String CONTENT_HEADER = "Content-Type";

  private static final String JSON = "application/json";

  private String stringify(Object obj) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    return ow.writeValueAsString(obj);
  }

  private FavoriteActivityEntity createEntity(ActivityEnum activityEnum) {
    FavoriteActivityEntity activityEntity = new FavoriteActivityEntity();
    activityEntity.setActivityTitle(activityEnum);
    activityEntity.setHighlighted(Boolean.FALSE);
    activityEntity.setEnabled(Boolean.TRUE);
    return activityEntity;
  }

  @Test
  @DisplayName("createFavoriteActivitySuccessTest")
  @WithMockUser(roles = "user_write")
  void createFavoriteActivitySuccessTest() throws Exception {
    FavoriteActivityCreateDto activityDto =
        new FavoriteActivityCreateDto(ActivityEnum.SEEDLING_REQUEST);

    FavoriteActivityEntity activityEntity = createEntity(ActivityEnum.SEEDLING_REQUEST);
    when(favoriteActivityService.createUserActivity(any())).thenReturn(activityEntity);

    mockMvc
        .perform(
            post(API_PATH)
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(stringify(activityDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.activityTitle").value("SEEDLING_REQUEST"))
        .andExpect(jsonPath("$.highlighted").value("false"))
        .andExpect(jsonPath("$.enabled").value("true"))
        .andReturn();
  }

  @Test
  @DisplayName("createFavoriteActivityUserNotFoundTest")
  @WithMockUser(roles = "user_write")
  void createFavoriteActivityUserNotFoundTest() throws Exception {
    FavoriteActivityCreateDto activityDto =
        new FavoriteActivityCreateDto(ActivityEnum.SEEDLING_REQUEST);

    when(favoriteActivityService.createUserActivity(any())).thenThrow(new UserNotFoundException());

    mockMvc
        .perform(
            post(API_PATH)
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(stringify(activityDto)))
        .andExpect(status().isNotFound())
        .andReturn();
  }

  @Test
  @DisplayName("createFavoriteActivityNotFoundTest")
  @WithMockUser(roles = "user_write")
  void createFavoriteActivityNotFoundTest() throws Exception {
    FavoriteActivityCreateDto activityDto =
        new FavoriteActivityCreateDto(null);

    when(favoriteActivityService.createUserActivity(any()))
        .thenThrow(new ActivityNotFoundException());

    mockMvc
        .perform(
            post(API_PATH)
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(stringify(activityDto)))
        .andExpect(status().isNotFound())
        .andReturn();
  }

  @Test
  @DisplayName("createFavoriteActivityDuplicatedTest")
  @WithMockUser(roles = "user_write")
  void createFavoriteActivityDuplicatedTest() throws Exception {
    FavoriteActivityCreateDto activityDto =
        new FavoriteActivityCreateDto(ActivityEnum.SEEDLING_REQUEST);

    String contentString = stringify(activityDto);
    FavoriteActivityEntity activityEntity = createEntity(ActivityEnum.SEEDLING_REQUEST);
    when(favoriteActivityService.createUserActivity(any())).thenReturn(activityEntity);

    mockMvc
        .perform(
            post(API_PATH)
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(contentString))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.activityTitle").value("SEEDLING_REQUEST"))
        .andExpect(jsonPath("$.highlighted").value("false"))
        .andExpect(jsonPath("$.enabled").value("true"))
        .andReturn();

    when(favoriteActivityService.createUserActivity(any()))
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

    FavoriteActivityEntity activityEntityOne = createEntity(ActivityEnum.SEEDLING_REQUEST);
    FavoriteActivityEntity activityEntityTwo = createEntity(ActivityEnum.SEEDLOT_REGISTRATION);
    activityEntityTwo.setHighlighted(Boolean.TRUE);
    List<FavoriteActivityEntity> favoriteActivityEntities =
        List.of(activityEntityOne, activityEntityTwo);
    when(favoriteActivityService.getAllUserFavoriteActivities())
        .thenReturn(favoriteActivityEntities);
    mockMvc
        .perform(
            get(API_PATH)
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].activityTitle").value("SEEDLING_REQUEST"))
        .andExpect(jsonPath("$[0].highlighted").value("false"))
        .andExpect(jsonPath("$[0].enabled").value("true"))
        .andExpect(
            jsonPath("$[1].activityTitle").value("SEEDLOT_REGISTRATION"))
        .andExpect(jsonPath("$[1].highlighted").value("true"))
        .andExpect(jsonPath("$[1].enabled").value("true"))
        .andReturn();
  }

  @Test
  @DisplayName("updateUserFavoriteActivity")
  @WithMockUser(roles = "user_write")
  void updateUserFavoriteActivity() throws Exception {
    FavoriteActivityEntity activityEntity = createEntity(ActivityEnum.PARENT_TREE_ORCHARD);
    activityEntity.setId(10000L);

    when(favoriteActivityService.createUserActivity(any())).thenReturn(activityEntity);

    mockMvc
        .perform(
            post(API_PATH)
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(stringify(activityEntity)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.activityTitle").value("PARENT_TREE_ORCHARD"))
        .andExpect(jsonPath("$.highlighted").value("false"))
        .andExpect(jsonPath("$.enabled").value("true"))
        .andReturn();

    FavoriteActivityEntity activityUpdated = activityEntity.withHighlighted(true);

    when(favoriteActivityService.updateUserActivity(any(), any())).thenReturn(activityUpdated);

    FavoriteActivityUpdateDto updateDto = new FavoriteActivityUpdateDto(true, true);

    mockMvc
        .perform(
            put(API_PATH + "/{id}", activityEntity.getId())
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(stringify(updateDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.activityTitle").value("PARENT_TREE_ORCHARD"))
        .andExpect(jsonPath("$.highlighted").value("true"))
        .andExpect(jsonPath("$.enabled").value("true"))
        .andReturn();
  }

  @Test
  @DisplayName("deleteUserFavoriteActivity")
  @WithMockUser(roles = "user_write")
  void deleteUserFavoriteActivity() throws Exception {
    FavoriteActivityEntity activityEntity = createEntity(ActivityEnum.PARENT_TREE_ORCHARD);
    activityEntity.setId(10000L);

    when(favoriteActivityService.createUserActivity(any())).thenReturn(activityEntity);

    mockMvc
        .perform(
            post(API_PATH)
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(stringify(activityEntity)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.activityTitle").value("PARENT_TREE_ORCHARD"))
        .andExpect(jsonPath("$.highlighted").value("false"))
        .andExpect(jsonPath("$.enabled").value("true"))
        .andReturn();

    FavoriteActivityEntity activityUpdated = activityEntity.withHighlighted(true);

    doNothing().when(favoriteActivityService).deleteUserActivity(any());

    mockMvc
        .perform(
            delete(API_PATH + "/{id}", activityUpdated.getId())
                .with(csrf().asHeader())
                .header(CONTENT_HEADER, JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  @DisplayName("createFavoriteActivityEndpointTest")
  void createFavoriteActivityEndpointTest() {
    FavoriteActivityEndpoint activityEndpoint = new FavoriteActivityEndpoint();
    activityEndpoint.setFavoriteActivityService(favoriteActivityService);
  }
}
