package ca.bc.gov.backendstartapi.entity.idclass;

import ca.bc.gov.backendstartapi.entity.ActiveOrchardSeedPlanningUnit;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/** Composite key for {@link ActiveOrchardSeedPlanningUnit}. */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ActiveOrchardSeedPlanningUnitId {

  @NonNull private String orchardId;

  @NonNull private int seedPlanningUnitId;
}
