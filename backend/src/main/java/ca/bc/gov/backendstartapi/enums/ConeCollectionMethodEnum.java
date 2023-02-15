package ca.bc.gov.backendstartapi.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Optional;
import lombok.Getter;
import lombok.ToString;

/** This enumeration represents a cone collection method code. */
@Getter
@ToString
@Schema(description = "This object represents a cone collection method code.")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ConeCollectionMethodEnum {
  AERIAL_RAKING("01", "Aerial raking"),
  AERIAL_CLIPPING_TOPPING("02", "Aerial clipping/topping"),
  FELLED_TREES("03", "Felled trees"),
  CLIMBING("04", "Climbing"),
  SQUIRREL_CACHE("05", "Squirrel cache"),
  GROUNG_LADDER_HYDRAULIC_LIFT("06", "Ground, Ladder and/or Hydraulic Lift"),
  UNKNOWN("07", "Unknown"),
  SQUIRREL_HARVESTING_DROPPING("08", "Squirrel harvesting/dropping");

  private final String code;
  private final String description;

  ConeCollectionMethodEnum(String code, String description) {
    this.code = code;
    this.description = description;
  }

  /**
   * Get a {@link ConeCollectionMethodEnum} by its code.
   *
   * @param code The code of the cone collection method. E.g.: 01, 02, 03...
   * @return An {@link Optional} of {@link ConeCollectionMethodEnum}
   */
  public static Optional<ConeCollectionMethodEnum> getByCode(String code) {
    for (ConeCollectionMethodEnum cone : values()) {
      if (cone.code.equals(code)) {
        return Optional.of(cone);
      }
    }
    return Optional.empty();
  }
}
