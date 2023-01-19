package ca.bc.gov.backendstartapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * This record represents a dto when updating a {@link
 * ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity}.
 *
 * @param highlighted A boolean representing if the activity is highlighted
 * @param enabled a boolean representing if the activity is enabled
 */
public record FavoriteActivityUpdateDto(
    @NotBlank @Pattern(regexp = "^true$|^false$", message = "allowed input: true of false")
        Boolean highlighted,
    @NotBlank @Pattern(regexp = "^true$|^false$", message = "allowed input: true of false")
        Boolean enabled) {}
