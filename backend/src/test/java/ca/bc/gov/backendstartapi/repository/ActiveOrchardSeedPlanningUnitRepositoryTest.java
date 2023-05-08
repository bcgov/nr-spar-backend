package ca.bc.gov.backendstartapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ActiveOrchardSeedPlanningUnitRepositoryTest {

  private final ActiveOrchardSeedPlanningUnitRepository repository;

  @Autowired
  ActiveOrchardSeedPlanningUnitRepositoryTest(
      ActiveOrchardSeedPlanningUnitRepository activeOrchardSeedPlanningUnitRepository) {
    repository = activeOrchardSeedPlanningUnitRepository;
  }

  @Test
  void testFindByOrchardIdAndActive() {
    var actives = repository.findByOrchardIdAndActive("144", true);
    assertEquals(1, actives.size());

    var active = actives.get(0);
    assertEquals("144", active.getOrchardId());
    assertEquals(108, active.getSeedPlanningUnitId());
    assertTrue(active.isActive());
    assertFalse(active.isRetired());
    assertFalse(active.isSpuNotAssigned());

    var inactives = repository.findByOrchardIdAndActive("144", false);
    assertEquals(1, inactives.size());

    var inactive = inactives.get(0);
    assertEquals("144", inactive.getOrchardId());
    assertEquals(55, inactive.getSeedPlanningUnitId());
    assertFalse(inactive.isActive());
    assertFalse(inactive.isRetired());
    assertFalse(inactive.isSpuNotAssigned());
  }
}
