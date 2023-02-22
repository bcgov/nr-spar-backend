package ca.bc.gov.backendstartapi.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/** This enumeration represents the origin of the seed. */
@Schema(description = "This object represents the origin of the seed.")
public enum GeneticClassEnum implements DescribedEnum {
  A("Orchard Seed or Cuttings"),
  B("Natural Stand Seed or Cuttings");

  private final String description;

  GeneticClassEnum(String description) {
    this.description = description;
  }

  @Override
  public String description() {
    return description;
  }
}
