package ca.bc.gov.backendstartapi.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/** This enumeration contains the SPAR activities. */
@Schema(description = "This enumeration contains the SPAR activities.", type = "string")
public enum ActivityEnum {
  SEEDLOT_REGISTRATION,
  PARENT_TREE_ORCHARD,
  SEEDLING_REQUEST;
}
