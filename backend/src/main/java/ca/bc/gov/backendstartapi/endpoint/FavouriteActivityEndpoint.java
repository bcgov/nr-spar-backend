package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.dto.FavouriteActivityCreateDto;
import ca.bc.gov.backendstartapi.dto.FavouriteActivityUpdateDto;
import ca.bc.gov.backendstartapi.entity.FavouriteActivityEntity;
import ca.bc.gov.backendstartapi.response.DefaultSpringExceptionResponse;
import ca.bc.gov.backendstartapi.response.ValidationExceptionResponse;
import ca.bc.gov.backendstartapi.service.FavouriteActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
    name = "FavouriteActivityEndpoint",
    description = "Resources to handle users' favourite activities")
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
  @Operation(
      summary = "Creates a Favourite Activity",
      description =
          """
          Creates a Favourite Activity to the logged user based on the activity
          title that should be one of ActivityEnum.
          """)
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "The Favourite Activity entity was successfully created",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FavouriteActivityEntity.class))),
        @ApiResponse(
            responseCode = "400",
            description = "The activity doesn't exists or is already defined to that user",
            content =
                @Content(
                    mediaType = "application/json",
                    schema =
                        @Schema(
                            oneOf = {
                              ValidationExceptionResponse.class,
                              DefaultSpringExceptionResponse.class
                            }))),
        @ApiResponse(
            responseCode = "401",
            description = "Access token is missing or invalid",
            content = @Content(schema = @Schema(implementation = Void.class)))
      })
  public ResponseEntity<FavouriteActivityEntity> createUserActivity(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Body containing the activity name that will be created",
              required = true,
              content =
                  @Content(schema = @Schema(implementation = FavouriteActivityCreateDto.class)))
          @Valid
          @RequestBody
          FavouriteActivityCreateDto createDto) {
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
  @Operation(
      summary = "Retrieves all users' favourite activities",
      description = "Retrieve all favourite activities bound to the logged user")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description =
                "An array with all registered favourite activities to the logged "
                    + "user or an empty array",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FavouriteActivityEntity.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Access token is missing or invalid",
            content = @Content(schema = @Schema(implementation = Void.class)))
      })
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
  @Operation(
      summary = "Updates a Favourite Activity",
      description =
          "Updates a Favourite Activity to the logged user based on the activity `id`"
              + " present at the address URL, in the path.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "The FavouriteActivityEntity was successfully updated",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FavouriteActivityEntity.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Access token is missing or invalid",
            content = @Content(schema = @Schema(implementation = Void.class))),
        @ApiResponse(
            responseCode = "404",
            description = "The FavouriteActivityEntity was not found",
            content = @Content(schema = @Schema(implementation = Void.class)))
      })
  public FavouriteActivityEntity updateFavoriteActivity(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Body containing the activity enabled and highlighted property values",
              required = true,
              content =
                  @Content(schema = @Schema(implementation = FavouriteActivityUpdateDto.class)))
          @Parameter(
              name = "id",
              in = ParameterIn.PATH,
              description = "FavouriteActivityEntity ID",
              required = true,
              schema = @Schema(type = "integer", format = "int64"))
          @PathVariable
          Long id,
      @Valid @RequestBody FavouriteActivityUpdateDto updateDto) {
    return favouriteActivityService.updateUserActivity(id, updateDto);
  }

  /**
   * Delete a user's {@link FavouriteActivityEntity}.
   *
   * @param id The id of the {@link FavouriteActivityEntity}
   */
  @DeleteMapping(value = "/{id}", produces = "application/json")
  @PreAuthorize("hasRole('user_write')")
  @Operation(
      summary = "Delete a Favourite Activity",
      description =
          "Remove a Favourite Activity to the logged user based on the activity `id` present"
              + " at the address URL, in the path.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "The FavouriteActivityEntity was successfully deleted",
            content = @Content(schema = @Schema(implementation = Void.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Access token is missing or invalid",
            content = @Content(schema = @Schema(implementation = Void.class))),
        @ApiResponse(
            responseCode = "404",
            description = "The FavouriteActivityEntity was not found",
            content = @Content(schema = @Schema(implementation = Void.class)))
      })
  public void deleteFavoriteActivity(
      @Parameter(
              name = "id",
              in = ParameterIn.PATH,
              description = "FavouriteActivityEntity ID",
              required = true,
              schema = @Schema(type = "integer", format = "int64"))
          @PathVariable
          Long id) {
    favouriteActivityService.deleteUserActivity(id);
  }
}
