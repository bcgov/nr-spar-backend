package ca.bc.gov.backendstartapi.dto;

import ca.bc.gov.backendstartapi.entity.FavouriteActivityEntity;
import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * This record represents a dto when creating a {@link FavouriteActivityEntity}.
 *
 * @param activity The activity from {@link ca.bc.gov.backendstartapi.enums.ActivityEnum}
 */
@Schema(description = "An object representing the request body when creating a favourite activity")
public record FavouriteActivityCreateDto(@NotNull ActivityEnum activity) {}
