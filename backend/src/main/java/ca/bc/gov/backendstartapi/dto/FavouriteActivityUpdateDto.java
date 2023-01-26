package ca.bc.gov.backendstartapi.dto;

import ca.bc.gov.backendstartapi.entity.FavouriteActivityEntity;
import jakarta.validation.constraints.NotBlank;

/**
 * This record represents a dto when updating a {@link FavouriteActivityEntity}.
 *
 * @param highlighted A boolean representing if the activity is highlighted
 * @param enabled a boolean representing if the activity is enabled
 */
public record FavouriteActivityUpdateDto(
    @NotBlank boolean highlighted, @NotBlank boolean enabled) {}
