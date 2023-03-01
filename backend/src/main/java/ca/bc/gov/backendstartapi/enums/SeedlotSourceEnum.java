package ca.bc.gov.backendstartapi.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/** This enumeration represents a seedlot source code. */
@Schema(description = "This object represents a seedlot source code.")
public enum SeedlotSourceEnum implements DescribedEnum {
  CUS("Custom Lot"),
  TPT("Tested Parent Trees"),
  UPT("Untested Parent Trees");

  private final String description;

  SeedlotSourceEnum(String description) {
    this.description = description;
  }

  @Override
  public String description() {
    return description;
  }
}
