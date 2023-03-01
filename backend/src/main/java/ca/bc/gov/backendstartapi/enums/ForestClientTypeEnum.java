package ca.bc.gov.backendstartapi.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A {@link DescribedEnum} of the possible types a {@link
 * ca.bc.gov.backendstartapi.dto.ForestClientDto forest client} can be.
 */
@Schema(
    description =
        """
The type of client, can be any of the following:<br>

A (Association)<br>
B (First Nation Band)<br>
C (Corporation)<br>
F (Ministry of Forests and Range)<br>
G (Government)<br>
I (Individual)<br>
L (Limited Partnership)<br>
P (General Partnership)<br>
R (First Nation Group)<br>
S (Society)<br>
T (First Nation Tribal Council)<br>
U (Unregistered Company)""")
public enum ForestClientTypeEnum implements DescribedEnum {
  A("Association"),
  B("First Nation Band"),
  C("Corporation"),
  F("Ministry of Forests and Range"),
  G("Government"),
  I("Individual"),
  L("Limited Partnership"),
  P("General Partnership"),
  R("First Nation Group"),
  S("Society"),
  T("First Nation Tribal Council"),
  U("Unregistered Company");

  private final String description;

  ForestClientTypeEnum(String description) {
    this.description = description;
  }

  @Override
  public String description() {
    return description;
  }
}
