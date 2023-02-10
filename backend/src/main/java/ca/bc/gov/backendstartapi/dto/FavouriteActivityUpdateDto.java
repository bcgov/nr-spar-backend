package ca.bc.gov.backendstartapi.dto;

import ca.bc.gov.backendstartapi.entity.FavouriteActivityEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * This record represents a dto when updating a {@link FavouriteActivityEntity}.
 *
 * @param highlighted A boolean representing if the activity is highlighted
 * @param enabled a boolean representing if the activity is enabled
 */
@Schema(description = "An object representing the request body when updating a favourite activity")
public record FavouriteActivityUpdateDto(@NotNull Boolean highlighted, @NotNull Boolean enabled) {}
