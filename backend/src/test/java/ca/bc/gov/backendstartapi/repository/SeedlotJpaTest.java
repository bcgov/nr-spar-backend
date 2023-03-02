package ca.bc.gov.backendstartapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.bc.gov.backendstartapi.enums.SeedlotStatusEnum;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SeedlotJpaTest extends SeedlotEntityJpaTest {

  @Autowired
  SeedlotJpaTest(SeedlotRepository seedlotRepository) {
    super(seedlotRepository);
  }

  @Test
  void create() {
    var savedSeedlot = createSeedlot("00000", SeedlotStatusEnum.SUB);
    var audit = savedSeedlot.getAuditInformation();

    assertEquals("user1", audit.getEntryUserId());
    assertEquals("user1", audit.getUpdateUserId());
    assertNotNull(audit.getEntryTimestamp());
    assertEquals(audit.getEntryTimestamp(), audit.getUpdateTimestamp());
    assertTrue(audit.getEntryTimestamp().until(LocalDateTime.now(), ChronoUnit.SECONDS) < 5);
    assertEquals(0, savedSeedlot.getRevisionCount());
  }

  @Test
  void update() {
    var savedSeedlot = createSeedlot("00000", SeedlotStatusEnum.SUB);
    var auditInfo = savedSeedlot.getAuditInformation();

    var entryUserId = auditInfo.getEntryUserId();
    var entryTimestamp = auditInfo.getEntryTimestamp();
    var updateUserId = auditInfo.getUpdateUserId();
    var updateTimestamp = auditInfo.getUpdateTimestamp();
    var revisionCount = savedSeedlot.getRevisionCount();

    var newUpdateUserId = updateUserId + 1;
    auditInfo.setUpdateUserId(newUpdateUserId);

    var newSavedSeedlot = repository.saveAndFlush(savedSeedlot);

    var newAuditInfo = newSavedSeedlot.getAuditInformation();
    assertEquals(entryUserId, newAuditInfo.getEntryUserId());
    assertEquals(entryTimestamp, newAuditInfo.getEntryTimestamp());
    assertEquals(newUpdateUserId, newAuditInfo.getUpdateUserId());
    assertTrue(updateTimestamp.isBefore(newAuditInfo.getUpdateTimestamp()));
    assertTrue(
        newAuditInfo.getUpdateTimestamp().until(LocalDateTime.now(), ChronoUnit.SECONDS) < 5);
    assertEquals(revisionCount + 1, newSavedSeedlot.getRevisionCount());
  }
}
