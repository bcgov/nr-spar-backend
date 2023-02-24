package ca.bc.gov.backendstartapi.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/** This enumeration represents a longitude code. */
@Schema(description = "This object represents a longitude code.")
public enum LongitudeCodeEnum implements DescribedEnum {
  E("East"),
  W("West");

  private final String description;

  LongitudeCodeEnum(String description) {
    this.description = description;
  }

  @Override
  public String description() {
    return description;
  }
}
