package ca.bc.gov.backendstartapi.dto;

import ca.bc.gov.backendstartapi.entity.FavouriteActivityEntity;
import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import jakarta.validation.constraints.NotNull;

/**
 * This record represents a dto when creating a {@link FavouriteActivityEntity}.
 *
 * @param activity The activity from {@link ca.bc.gov.backendstartapi.enums.ActivityEnum}
 */
public record FavouriteActivityCreateDto(@NotNull ActivityEnum activity) {}
