package ca.bc.gov.backendstartapi.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Optional;
import lombok.Getter;
import lombok.ToString;

/** This enumeration represents a latitude code. */
@Getter
@ToString
@Schema(description = "This object represents a latitude code.")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LatitudeCodeEnum {
  NORTH('N', "North"),
  SOUTH('S', "South");

  @Schema(description = "Code of the latitude", example = "N")
  private final char code;

  @Schema(description = "Description of the latitude", example = "North")
  private final String description;

  LatitudeCodeEnum(char code, String description) {
    this.code = code;
    this.description = description;
  }

  /**
   * Get a {@link LatitudeCodeEnum} instance by its code.
   *
   * @param code The code of the latitude. E.g.: S or N
   * @return An {@link Optional} of {@link LatitudeCodeEnum}
   */
  public static Optional<LatitudeCodeEnum> getByCode(char code) {
    for (LatitudeCodeEnum colLat : values()) {
      if (colLat.code == code) {
        return Optional.of(colLat);
      }
    }
    return Optional.empty();
  }
}

