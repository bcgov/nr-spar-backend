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

  private String stringify(Object obj) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    return ow.writeValueAsString(obj);
  }

  private FavoriteActivityEntity createEntity(ActivityEnum activityEnum) {
    FavoriteActivityEntity activityEntity = new FavoriteActivityEntity();
    activityEntity.setActivityTitle(activityEnum.getTitle());
    activityEntity.setHighlighted(Boolean.FALSE);
    activityEntity.setEnabled(Boolean.TRUE);
    return activityEntity;
  }

  @Test
  @DisplayName("createFavoriteActivitySuccessTest")
  @WithMockUser(roles = "user_write")
  void createFavoriteActivitySuccessTest() throws Exception {
    FavoriteActivityCreateDto activityDto =
        new FavoriteActivityCreateDto(ActivityEnum.SEEDLING_REQUEST.getTitle());

    FavoriteActivityEntity activityEntity = createEntity(ActivityEnum.SEEDLING_REQUEST);
    when(favoriteActivityService.createUserActivity(any())).thenReturn(activityEntity);

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
    FavoriteActivityCreateDto activityDto =
        new FavoriteActivityCreateDto(ActivityEnum.SEEDLING_REQUEST.getTitle());

    when(favoriteActivityService.createUserActivity(any())).thenThrow(UserNotFoundException.class);

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
    FavoriteActivityCreateDto activityDto = new FavoriteActivityCreateDto("Any Title Here");

    when(favoriteActivityService.createUserActivity(any()))
        .thenThrow(ActivityNotFoundException.class);

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
    FavoriteActivityCreateDto activityDto =
        new FavoriteActivityCreateDto(ActivityEnum.SEEDLING_REQUEST.getTitle());

    String contentString = stringify(activityDto);
    FavoriteActivityEntity activityEntity = createEntity(ActivityEnum.SEEDLING_REQUEST);
    when(favoriteActivityService.createUserActivity(any())).thenReturn(activityEntity);

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

    when(favoriteActivityService.createUserActivity(any()))
        .thenThrow(FavoriteActivityExistsToUser.class);

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
            get("/api/favorite_activity")
                .with(csrf().asHeader())
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].activityTitle").value("Seedling request"))
        .andExpect(jsonPath("$[0].highlighted").value("false"))
        .andExpect(jsonPath("$[0].enabled").value("true"))
        .andExpect(
            jsonPath("$[1].activityTitle").value(ActivityEnum.SEEDLOT_REGISTRATION.getTitle()))
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

    String title = ActivityEnum.PARENT_TREE_ORCHARD.getTitle();
    mockMvc
        .perform(
            post("/api/favorite_activity")
                .with(csrf().asHeader())
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(stringify(activityEntity)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.activityTitle").value(title))
        .andExpect(jsonPath("$.highlighted").value("false"))
        .andExpect(jsonPath("$.enabled").value("true"))
        .andReturn();

    FavoriteActivityEntity activityUpdated = activityEntity.withHighlighted(true);

    when(favoriteActivityService.updateUserActivity(any(), any())).thenReturn(activityUpdated);

    FavoriteActivityUpdateDto updateDto = new FavoriteActivityUpdateDto(true, true);

    mockMvc
        .perform(
            put("/api/favorite_activity/{id}", activityEntity.getId())
                .with(csrf().asHeader())
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(stringify(updateDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.activityTitle").value(title))
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

    String title = ActivityEnum.PARENT_TREE_ORCHARD.getTitle();
    mockMvc
        .perform(
            post("/api/favorite_activity")
                .with(csrf().asHeader())
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(stringify(activityEntity)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.activityTitle").value(title))
        .andExpect(jsonPath("$.highlighted").value("false"))
        .andExpect(jsonPath("$.enabled").value("true"))
        .andReturn();

    FavoriteActivityEntity activityUpdated = activityEntity.withHighlighted(true);

    doNothing().when(favoriteActivityService).deleteUserActivity(any());

    mockMvc
        .perform(
            delete("/api/favorite_activity/{id}", activityEntity.getId())
                .with(csrf().asHeader())
                .header("Content-Type", "application/json")
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