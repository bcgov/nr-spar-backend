package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.dto.FavoriteActivityDto;
import ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity;
import ca.bc.gov.backendstartapi.entity.UserEntity;
import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import ca.bc.gov.backendstartapi.repository.FavoriteActivityRepository;
import ca.bc.gov.backendstartapi.repository.UserRepository;
import ca.bc.gov.backendstartapi.response.BaseResponse;
import ca.bc.gov.backendstartapi.service.FavoriteActivityService;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.apache.bcel.classfile.Module.Open;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  // create - post
  @PostMapping(consumes = "application/json", produces = "application/json")
  public FavoriteActivityEntity createUserActivity(@RequestBody FavoriteActivityDto activityDto) {
    return favoriteActivityService.createUserActivity(activityDto);
  }

  // retrieve - get

  // update - put
}
