package ca.bc.gov.backendstartapi.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/** This enumeration represents a latitude code. */
@Schema(description = "This object represents a latitude code.")
public enum LatitudeCodeEnum implements DescribedEnum {
  N("North"),
  S("South");

  @Schema(description = "Description of the latitude", example = "North")
  private final String description;

  LatitudeCodeEnum(String description) {
    this.description = description;
  }

  @Override
  public String description() {
    return description;
  }
}
