package ca.bc.gov.backendstartapi.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/** This enumeration contains the SPAR activities. */
@Schema(description = "This enumeration contains the SPAR activities.", type = "string")
public enum ActivityEnum {
  CREATE_A_CLASS_SEEDLOT,
  EXISTING_SEEDLOTS,
  SEEDLOT_DASHBOARD,
  SEEDLOT_REGISTRATION;
}
