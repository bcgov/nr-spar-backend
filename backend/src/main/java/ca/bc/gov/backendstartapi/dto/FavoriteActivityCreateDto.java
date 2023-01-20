package ca.bc.gov.backendstartapi.dto;

import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * This record represents a dto when creating a {@link
 * ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity}.
 *
 * @param activity The activity from {@link ca.bc.gov.backendstartapi.enums.ActivityEnum}
 */
public record FavoriteActivityCreateDto(@Size(min = 2, max = 20) @NotBlank ActivityEnum activity) {}
