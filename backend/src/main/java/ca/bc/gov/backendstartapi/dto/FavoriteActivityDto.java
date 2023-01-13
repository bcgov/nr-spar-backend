package ca.bc.gov.backendstartapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FavoriteActivityDto(
    @Size(min = 2, max = 20) @NotBlank String title
) {}
