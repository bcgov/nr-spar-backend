package ca.bc.gov.backendstartapi.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "An object with fields name and the respective error massages")
record FieldIssue(String fieldName, String fieldMessage) {}
