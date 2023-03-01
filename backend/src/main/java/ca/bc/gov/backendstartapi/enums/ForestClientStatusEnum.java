package ca.bc.gov.backendstartapi.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A {@link DescribedEnum} of the possible status a {@link
 * ca.bc.gov.backendstartapi.dto.ForestClientDto forest client} can have.
 */
@Schema(
    description =
        """
The status of the client, can be any of the following:<br>

ACT (Active)<br>
DAC (Deactivated)<br>
DEC (Deceased)<br>
REC (Receivership)<br>
SPN (Suspended)""")
public enum ForestClientStatusEnum implements DescribedEnum {
  ACT("Active"),
  DAC("Deactivated"),
  DEC("Deceased"),
  REC("Receivership"),
  SPN("Suspended");

  private final String description;

  ForestClientStatusEnum(String description) {
    this.description = description;
  }

  @Override
  public String description() {
    return description;
  }
}
