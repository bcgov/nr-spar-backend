package ca.bc.gov.backendstartapi.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/** Possible status for Seed lots. */
@Schema(description = "The possible status for a seed lot.")
public enum SeedlotStatusEnum implements DescribedEnum {
  APP("Approved"),
  CAN("Cancelled"),
  COM("Complete"),
  INC("Incomplete"),
  PND("Pending"),
  EXP("Expired"),
  SUB("Submitted");

  private final String description;

  SeedlotStatusEnum(String description) {
    this.description = description;
  }

  @Override
  public String description() {
    return description;
  }
}
