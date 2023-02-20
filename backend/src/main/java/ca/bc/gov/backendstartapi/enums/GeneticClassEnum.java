package ca.bc.gov.backendstartapi.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/** This enumeration represents a specie genetic class. */
@Schema(description = "This object represents a specie genetic class.")
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
