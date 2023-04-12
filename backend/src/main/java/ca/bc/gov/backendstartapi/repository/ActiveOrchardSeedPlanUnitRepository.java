package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.ActiveOrchardSeedPlanUnit;
import ca.bc.gov.backendstartapi.entity.idclass.ActiveOrchardSeedPlanUnitId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/** The repository for {@link ActiveOrchardSeedPlanUnit ActiveOrchardSeedPlanUnits}. */
public interface ActiveOrchardSeedPlanUnitRepository
    extends JpaRepository<ActiveOrchardSeedPlanUnit, ActiveOrchardSeedPlanUnitId> {

  List<ActiveOrchardSeedPlanUnit> findByOrchardIdAndActive(String orchardId, boolean active);
}
