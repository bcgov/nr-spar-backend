package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.entity.FavoriteActivity;
import ca.bc.gov.backendstartapi.entity.User;
import ca.bc.gov.backendstartapi.handlers.AuthenticationHandler;
import ca.bc.gov.backendstartapi.repository.FavoriteActivityRepository;
import ca.bc.gov.backendstartapi.repository.UserRepository;
import ca.bc.gov.backendstartapi.security.UserInfo;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Setter
@RestController
@NoArgsConstructor
@RequestMapping("/api/favorite-activity")
public class FavoriteActivityEndpoint {

  private FavoriteActivityRepository favoriteActivityRepository;

  private AuthenticationHandler authenticationHandler;

  private UserRepository userRepository;

  @Autowired
  public FavoriteActivityEndpoint(FavoriteActivityRepository favoriteActivityRepository,
      AuthenticationHandler authenticationHandler, UserRepository userRepository) {
    this.favoriteActivityRepository = favoriteActivityRepository;
    this.authenticationHandler = authenticationHandler;
    this.userRepository = userRepository;
  }

  @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('user_read')")
  public List<FavoriteActivity> getFavoriteActivities() {
    UserInfo userInfo = authenticationHandler.getUserInfo();
    Optional<User> user = userRepository.findUserByEmail(userInfo.getEmail());
    Long userId = user.orElseThrow().getId();
    return favoriteActivityRepository.findAllByUserId(userId);
  }
}
