package ca.bc.gov.backendstartapi.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/** This enumeration represents a method code to determine male/female gametic contribution. */
@Schema(
    description =
        "This object represents a method code to determine male/female gametic contribution.")
public enum MaleFemaleMethodologyEnum implements DescribedEnum {
  F1("Visual Estimate"),
  F2("Measured Cone Volume"),
  F3("Cone Weight"),
  F4("Cone Number from Weight"),
  F5("Cone Number from Standard Volume"),
  F6("Sample of Seeds"),
  F7("Filled Seeds"),
  F8("Ramet Proportion by Clone"),
  F9("Ramet Proportion by Age and Expected Production"),
  M1("Portion of Ramets in Orchard"),
  M2(" Pollen Volume Estimate by Partial Survey"),
  M3("Pollen Volume Estimate by 100% Survey"),
  M4("Ramet Proportion by Clone"),
  M5("Ramet Proportion by Age and Expected Production");

  private final String description;

  MaleFemaleMethodologyEnum(String description) {
    this.description = description;
  }

  @Override
  public String description() {
    return description;
  }
}
