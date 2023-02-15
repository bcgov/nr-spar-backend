package ca.bc.gov.backendstartapi.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Optional;
import lombok.Getter;
import lombok.ToString;

/** This enumeration represents a longitude code. */
@Getter
@ToString
@Schema(description = "This object represents a longitude code.")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LongitudeCodeEnum {
  EAST('E', "East"),
  WEST('W', "West");

  private final char code;
  private final String description;

  LongitudeCodeEnum(char code, String description) {
    this.code = code;
    this.description = description;
  }

  /**
   * Get a {@link LongitudeCodeEnum} instance by its code.
   *
   * @param code The code of the longitude. E.g.: E or W
   * @return An {@link Optional} of {@link LongitudeCodeEnum}
   */
  public static Optional<LongitudeCodeEnum> getByCode(char code) {
    for (LongitudeCodeEnum colLon : values()) {
      if (colLon.code == code) {
        return Optional.of(colLon);
      }
    }
    return Optional.empty();
  }
}
