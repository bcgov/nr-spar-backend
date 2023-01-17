package ca.bc.gov.backendstartapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * This record represents a dto when creating a {@link
 * ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity}.
 *
 * @param title The title of the activity, from {@link ca.bc.gov.backendstartapi.enums.ActivityEnum}
 */
public record FavoriteActivityCreateDto(@Size(min = 2, max = 20) @NotBlank String title) {}
