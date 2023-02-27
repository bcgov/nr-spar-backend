package ca.bc.gov.backendstartapi.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/** This enumeration represents a genetic worth code. */
@Schema(description = "This object represents a genetic worth code.")
public enum GeneticWorthEnum implements DescribedEnum {
  GVO("Volume Growth"),
  WVE("Wood Velocity Measures"),
  WWD("Wood Density"),
  WDU("Durability"),
  AD("Deer"),
  DFS("Dothistroma Needle Blight (Dothistroma septosporum)"),
  DFU("Cedar Leaf Blight (Didymascella thujina)"),
  DFW("Swiss Needle Cast (Phaeocryptopus gaumanni)"),
  DSB("White pine Blister Rust (Cronartium ribicola)"),
  DSC("Comandra Blister Rust (Cronartium comandrae)"),
  DSG("Western Gall Rust (Endocronartium harknessii)"),
  IWS("White pine Weevil (on Spruce) (Pissodes strobi)");

  private final String description;

  GeneticWorthEnum(String description) {
    this.description = description;
  }

  @Override
  public String description() {
    return description;
  }
}
