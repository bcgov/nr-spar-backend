package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.dto.FavouriteActivityCreateDto;
import ca.bc.gov.backendstartapi.dto.FavouriteActivityUpdateDto;
import ca.bc.gov.backendstartapi.entity.FavouriteActivityEntity;
import ca.bc.gov.backendstartapi.service.FavouriteActivityService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** This class contains all {@link FavouriteActivityEntity} resources that a user needs. */
@Setter
@NoArgsConstructor
@RestController
@RequestMapping("/api/favourite-activities")
public class FavouriteActivityEndpoint {

  private FavouriteActivityService favouriteActivityService;

  @Autowired
  FavouriteActivityEndpoint(FavouriteActivityService favouriteActivityService) {
    this.favouriteActivityService = favouriteActivityService;
  }

  /**
   * Creates to the logged user a {@link FavouriteActivityEntity} based on the activity title, that
   * comes from {@link ca.bc.gov.backendstartapi.enums.ActivityEnum}.
   *
   * @param createDto a {@link FavouriteActivityCreateDto} with the activity title
   * @return a {@link FavouriteActivityEntity} created
   */
  @PostMapping(consumes = "application/json", produces = "application/json")
  @PreAuthorize("hasRole('user_write')")
  public ResponseEntity<FavouriteActivityEntity> createUserActivity(
      @Valid @RequestBody FavouriteActivityCreateDto createDto) {

    FavouriteActivityEntity entity = favouriteActivityService.createUserActivity(createDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(entity);
  }

  /**
   * Retrieve all {@link FavouriteActivityEntity} bound to the user that made the request.
   *
   * @return a list of {@link FavouriteActivityEntity}
   */
  @GetMapping(produces = "application/json")
  @PreAuthorize("hasRole('user_read')")
  public List<FavouriteActivityEntity> getUserActivities() {
    return favouriteActivityService.getAllUserFavoriteActivities();
  }

  /**
   * Update a {@link FavouriteActivityEntity} of the logged user.
   *
   * @param id The id of the {@link FavouriteActivityEntity}
   * @param updateDto a {@link FavouriteActivityUpdateDto} containing highlighted and enabled states
   * @return the {@link FavouriteActivityEntity} updated
   */
  @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
  @PreAuthorize("hasRole('user_write')")
  public FavouriteActivityEntity updateFavoriteActivity(
      @PathVariable Long id, @Valid @RequestBody FavouriteActivityUpdateDto updateDto) {
    return favouriteActivityService.updateUserActivity(id, updateDto);
  }

  /**
   * Delete a user's {@link FavouriteActivityEntity}.
   *
   * @param id The id of the {@link FavouriteActivityEntity}
   */
  @DeleteMapping(value = "/{id}", produces = "application/json")
  @PreAuthorize("hasRole('user_write')")
  public void deleteFavoriteActivity(@PathVariable Long id) {
    favouriteActivityService.deleteUserActivity(id);
  }
}
