package ca.bc.gov.backendstartapi.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Optional;
import lombok.Getter;
import lombok.ToString;

/** This enumeration represents a specie genetic class. */
@Getter
@ToString
@Schema(description = "This object represents a specie genetic class.")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GeneticClassEnum {
  A_CLASS('A', "Orchard Seed or Cuttings"),
  B_CLASS('B', "Natural Stand Seed or Cuttings");

  private final char code;
  private final String description;

  GeneticClassEnum(char code, String description) {
    this.code = code;
    this.description = description;
  }

  /**
   * Get a {@link GeneticClassEnum} enum instance by its code.
   *
   * @param code The code of the genetic class
   * @return an {@link Optional} of {@link GeneticClassEnum}
   */
  public static Optional<GeneticClassEnum> getByCode(char code) {
    for (GeneticClassEnum genClass : values()) {
      if (genClass.code == code) {
        return Optional.of(genClass);
      }
    }
    return Optional.empty();
  }
}
