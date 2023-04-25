package ca.bc.gov.backendstartapi.dto;

import ca.bc.gov.backendstartapi.entity.FavouriteActivityEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * This record represents a dto when creating a {@link FavouriteActivityEntity}.
 *
 * @param activity The activity or page name
 */
@Schema(description = "An object representing the request body when creating a favourite activity")
public record FavouriteActivityCreateDto(@NotNull String activity) {}
