package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.dto.UserSampleDto;
import ca.bc.gov.backendstartapi.exception.UserNotFoundException;
import ca.bc.gov.backendstartapi.repository.UserSampleRepository;
import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** This class exposes user related endpoints. */
@NoArgsConstructor
@RestController
@RequestMapping("/api/users")
@Setter
public class UserSampleEndpoint {

  private UserSampleRepository userSampleRepository;

  @Autowired
  public UserSampleEndpoint(UserSampleRepository userSampleRepository) {
    this.userSampleRepository = userSampleRepository;
  }

  /**
   * Create a user with first and last name.
   *
   * @param user a UserDto containing both first and last name
   * @return a UserDto instance containing the new user info
   */
  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('user_write')")
  public UserSampleDto create(@Valid @RequestBody UserSampleDto user) {
    return userSampleRepository.save(user);
  }

  /**
   * Get a list of users given the last name.
   *
   * @param firstName user's first name
   * @return A List containing all found users.
   */
  @GetMapping(
      value = "/find-all-by-first-name/{firstName}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('user_read')")
  public List<UserSampleDto> readByFirstName(@PathVariable("firstName") String firstName) {
    List<UserSampleDto> userList = userSampleRepository.findAllByFirstName(firstName);
    if (userList.isEmpty()) {
      throw new UserNotFoundException();
    }
    return userList;
  }

  /**
   * Get a list of users given the last name.
   *
   * @param lastName user's last name
   * @return A List containing all found users.
   */
  @GetMapping(
      value = "/find-all-by-last-name/{lastName}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('user_read')")
  public List<UserSampleDto> readByLastName(@PathVariable("lastName") String lastName) {
    List<UserSampleDto> userList = userSampleRepository.findAllByLastName(lastName);
    if (userList.isEmpty()) {
      throw new UserNotFoundException();
    }
    return userList;
  }

  /**
   * Get a user given his first and last name.
   *
   * @param firstName user's first name
   * @param lastName user's last name
   * @return a UserDto instance containing the found user or a 404 if not found.
   */
  @GetMapping(value = "/find/{firstName}/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('user_read')")
  public UserSampleDto readByUser(
      @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
    Optional<UserSampleDto> userDtoOp = userSampleRepository.find(firstName, lastName);
    if (userDtoOp.isEmpty()) {
      throw new UserNotFoundException();
    }

    return userDtoOp.get();
  }

  /**
   * Get a list with all registered users.
   *
   * @return a Collection containing all found users or a 404 if not found.
   */
  @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('user_read')")
  public Collection<UserSampleDto> readAllUsers() {
    return userSampleRepository.findAll();
  }

  /**
   * Delete a user given his first and last name.
   *
   * @param firstName user's first name
   * @param lastName user's last name
   * @return a UserDto instance containing the removed user info.
   */
  @DeleteMapping(value = "/{firstName}/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('user_write')")
  public UserSampleDto deleteUser(
      @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
    return userSampleRepository.delete(new UserSampleDto(firstName, lastName));
  }
}
