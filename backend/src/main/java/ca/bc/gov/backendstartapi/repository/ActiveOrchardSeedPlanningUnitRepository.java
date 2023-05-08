package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.ActiveOrchardSeedPlanningUnit;
import ca.bc.gov.backendstartapi.entity.idclass.ActiveOrchardSeedPlanningUnitId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/** The repository for {@link ActiveOrchardSeedPlanningUnit ActiveOrchardSeedPlanningUnits}. */
public interface ActiveOrchardSeedPlanningUnitRepository
    extends JpaRepository<ActiveOrchardSeedPlanningUnit, ActiveOrchardSeedPlanningUnitId> {

  List<ActiveOrchardSeedPlanningUnit> findByOrchardIdAndActive(String orchardId, boolean active);
}
