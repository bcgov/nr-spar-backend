package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.dto.FavoriteActivityCreateDto;
import ca.bc.gov.backendstartapi.dto.FavoriteActivityUpdateDto;
import ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity;
import ca.bc.gov.backendstartapi.service.FavoriteActivityService;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** This class contains all {@link FavoriteActivityEntity} resources that a user needs. */
@Setter
@NoArgsConstructor
@RestController
@RequestMapping("/api/favorite_activity")
public class FavoriteActivityEndpoint {

  private FavoriteActivityService favoriteActivityService;

  /**
   * Creates a {@link FavoriteActivityEndpoint} instance.
   *
   * @param favoriteActivityService a {@link FavoriteActivityService} instance
   */
  @Autowired
  public FavoriteActivityEndpoint(FavoriteActivityService favoriteActivityService) {
    this.favoriteActivityService = favoriteActivityService;
  }

  /**
   * Creates to the logged user a {@link FavoriteActivityEntity} based on the activity title, that
   * comes from {@link ca.bc.gov.backendstartapi.enums.ActivityEnum}.
   *
   * @param createDto a {@link FavoriteActivityCreateDto} with the activity title
   * @return a {@link FavoriteActivityEntity} created
   */
  @PostMapping(consumes = "application/json", produces = "application/json")
  @PreAuthorize("hasRole('user_write')")
  public FavoriteActivityEntity createUserActivity(
      @RequestBody FavoriteActivityCreateDto createDto) {
    return favoriteActivityService.createUserActivity(createDto);
  }

  /**
   * Retrieve all {@link FavoriteActivityEntity} bounded to the user that made the request.
   *
   * @return a list of {@link FavoriteActivityEntity}
   */
  @GetMapping(produces = "application/json")
  @PreAuthorize("hasRole('user_read')")
  public List<FavoriteActivityEntity> getUserActivities() {
    return favoriteActivityService.getAllUserFavoriteActivities();
  }

  /**
   * Update a {@link FavoriteActivityEntity} of the logged user.
   *
   * @param id The id of the {@link FavoriteActivityEntity}
   * @param updateDto a {@link FavoriteActivityUpdateDto} containing highlighted and enabled states
   * @return the {@link FavoriteActivityEntity} updated
   */
  @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
  @PreAuthorize("hasRole('user_write')")
  public FavoriteActivityEntity updateFavoriteActivity(
      @PathVariable Long id, @RequestBody FavoriteActivityUpdateDto updateDto) {
    return favoriteActivityService.updateUserActivity(id, updateDto);
  }

  /**
   * Delete a user's {@link FavoriteActivityEntity}.
   *
   * @param id The id of the {@link FavoriteActivityEntity}
   */
  @DeleteMapping(value = "/{id}", produces = "application/json")
  @PreAuthorize("hasRole('user_write')")
  public void deleteFavoriteActivity(@PathVariable Long id) {
    favoriteActivityService.deleteUserActivity(id);
  }
}
