package ca.bc.gov.backendstartapi.vo.parser;

import static ca.bc.gov.backendstartapi.enums.parser.ConeAndPollenCountHeader.CONE_COUNT;
import static ca.bc.gov.backendstartapi.enums.parser.ConeAndPollenCountHeader.PARENT_TREE_NUMBER;
import static ca.bc.gov.backendstartapi.enums.parser.ConeAndPollenCountHeader.POLLEN_CONTAMINATION;
import static ca.bc.gov.backendstartapi.enums.parser.ConeAndPollenCountHeader.POLLEN_COUNT;
import static ca.bc.gov.backendstartapi.enums.parser.ConeAndPollenCountHeader.SMP_SUCCESS;

import ca.bc.gov.backendstartapi.enums.parser.ConeAndPollenCountHeader;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;

/** Information about the contribution of a parent tree to a seedlot. */
public record ConeAndPollenCount(
    @Schema(description = "The identifier of the tree inside its orchard.", minimum = "1")
        int parentTreeNumber,
    @Schema(
            description =
                "The number of cones counted or estimated from this parent tree in the seedlot.",
            minimum = "0")
        double coneCount,
    @Schema(
            description =
                "The amount of pollen counted or estimated for each parent tree in the seedlot.",
            minimum = "0")
        double pollenCount,
    @Schema(
            description =
                """
                Estimated percentage of success of the supplemental mass pollination mix on the
                parent tree.""",
            minimum = "0")
        int smpSuccess,
    @Schema(description = "Percentage of non-orchard pollen contamination.", minimum = "0")
        int pollenContamination)
    implements CsvParsingResult {

  /** Create an instance from the information of the map. */
  public static ConeAndPollenCount fromMap(Map<ConeAndPollenCountHeader, Number> map) {
    return new ConeAndPollenCount(
        map.get(PARENT_TREE_NUMBER).intValue(),
        map.get(CONE_COUNT).doubleValue(),
        map.get(POLLEN_COUNT).doubleValue(),
        map.get(SMP_SUCCESS).intValue(),
        map.get(POLLEN_CONTAMINATION).intValue());
  }

  /** Validation of the constructor parameters. */
  public ConeAndPollenCount {
    if (parentTreeNumber <= 0) {
      throw new IllegalArgumentException(PARENT_TREE_NUMBER + " number must be positive");
    }
    if (coneCount < 0) {
      throw new IllegalArgumentException(CONE_COUNT + " cannot be negative");
    }
    if (pollenCount < 0) {
      throw new IllegalArgumentException(POLLEN_COUNT + " cannot be negative");
    }
    if (smpSuccess < 0) {
      throw new IllegalArgumentException(SMP_SUCCESS + " cannot be negative");
    }
    if (pollenContamination < 0) {
      throw new IllegalArgumentException(POLLEN_CONTAMINATION + " cannot be negative");
    }
  }
}
