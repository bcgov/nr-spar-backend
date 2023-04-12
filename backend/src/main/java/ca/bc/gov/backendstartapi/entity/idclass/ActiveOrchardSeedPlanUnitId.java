package ca.bc.gov.backendstartapi.entity.idclass;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/** Composite key for {@link ca.bc.gov.backendstartapi.entity.ActiveOrchardSeedPlanUnit}. */
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor
public class ActiveOrchardSeedPlanUnitId {

  @NonNull private String orchardId;

  @NonNull private int seedPlanUnitId;
}
