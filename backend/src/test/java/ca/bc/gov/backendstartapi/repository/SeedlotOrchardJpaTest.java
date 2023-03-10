package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.embeddable.AuditInformation;
import ca.bc.gov.backendstartapi.entity.seedlot.SeedlotOrchard;
import ca.bc.gov.backendstartapi.enums.SeedlotStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SeedlotOrchardJpaTest extends SeedlotEntityJpaTest {

  private final SeedlotOrchardRepository repository;

  @Autowired
  protected SeedlotOrchardJpaTest(
      SeedlotRepository seedlotRepository, SeedlotOrchardRepository seedlotOrchardRepository) {
    super(seedlotRepository);
    repository = seedlotOrchardRepository;
  }

  @Test
  void create() {
    var seedlot = createSeedlot("00000", SeedlotStatusEnum.SUB);
    var seedlotOrchard = new SeedlotOrchard(seedlot, "ABC");
    seedlotOrchard.setPrimary(true);
    seedlotOrchard.setAuditInformation(new AuditInformation("user1"));

    repository.saveAndFlush(seedlotOrchard);
  }
}
