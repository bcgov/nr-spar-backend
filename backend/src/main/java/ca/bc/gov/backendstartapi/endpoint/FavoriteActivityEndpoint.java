package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.dto.FavoriteActivityDto;
import ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity;
import ca.bc.gov.backendstartapi.service.FavoriteActivityService;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** This class contains all users' favorite activity endpoints. */
@Setter
@NoArgsConstructor
@RestController
@RequestMapping("/api/favorite_activity")
public class FavoriteActivityEndpoint {

  private FavoriteActivityService favoriteActivityService;

  /**
   * Creates a FavoriteActivityEndpoint instance.
   *
   * @param favoriteActivityService a FavoriteActivityService instance
   */
  @Autowired
  public FavoriteActivityEndpoint(FavoriteActivityService favoriteActivityService) {
    this.favoriteActivityService = favoriteActivityService;
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  @PreAuthorize("hasRole('user_write')")
  public FavoriteActivityEntity createUserActivity(@RequestBody FavoriteActivityDto activityDto) {
    return favoriteActivityService.createUserActivity(activityDto);
  }

  @GetMapping(produces = "application/json")
  public List<FavoriteActivityEntity> getUserActivities() {
    return favoriteActivityService.getAllUserFavoriteActivities();
  }

  @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
  public FavoriteActivityEntity updateFavoriteActivity() {
    // TODO: keep going from here!!
    return null;
  }

  @DeleteMapping(value = "/{id}", produces = "application/json")
  public void deleteFavoriteActivity() {
    //
  }
}
