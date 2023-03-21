package ca.bc.gov.backendstartapi.vo.parser;

import static ca.bc.gov.backendstartapi.enums.parser.SmpMixHeader.PARENT_TREE_NUMBER;
import static ca.bc.gov.backendstartapi.enums.parser.SmpMixHeader.POLLEN_VOLUME_ML;

import ca.bc.gov.backendstartapi.enums.parser.SmpMixHeader;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Map;
import lombok.NonNull;

/** Data about the volume. */
public record SmpMixVolume(
    @Schema(description = "The identifier of the tree inside its orchard.", minimum = "1")
        int parentTreeNumber,
    @NonNull
        @Schema(
            description =
                """
                The amount of parent tree material used for calculating the proportion of mix, in
                millilitres.""",
            minimum = "0")
        BigDecimal pollenVolume)
    implements CsvParsingResult {

  /** Create an instance from the information of the map. */
  public static SmpMixVolume fromMap(Map<SmpMixHeader, Number> map) {
    return new SmpMixVolume(
        map.get(PARENT_TREE_NUMBER).intValue(),
        BigDecimal.valueOf(map.get(POLLEN_VOLUME_ML).doubleValue()));
  }

  /** Validation of the constructor parameters. */
  public SmpMixVolume {
    if (parentTreeNumber <= 0) {
      throw new IllegalArgumentException(PARENT_TREE_NUMBER + " must be positive");
    }
    if (pollenVolume.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException(PARENT_TREE_NUMBER + " cannot be negative");
    }
  }
}
