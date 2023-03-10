package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.embeddable.AuditInformation;
import ca.bc.gov.backendstartapi.entity.seedlot.SeedlotCollectionMethod;
import ca.bc.gov.backendstartapi.enums.ConeCollectionMethodEnum;
import ca.bc.gov.backendstartapi.enums.SeedlotStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SeedlotCollectionMethodJpaTest extends SeedlotEntityJpaTest {

  private final SeedlotCollectionMethodRepository repository;

  @Autowired
  SeedlotCollectionMethodJpaTest(
      SeedlotRepository seedlotRepository,
      SeedlotCollectionMethodRepository seedlotCollectionMethodRepository) {
    super(seedlotRepository);
    repository = seedlotCollectionMethodRepository;
  }

  @Test
  void create() {
    var seedlot = createSeedlot("00000", SeedlotStatusEnum.SUB);
    var seedlotCollectionMethod =
        new SeedlotCollectionMethod(seedlot, ConeCollectionMethodEnum.CLIMBING);
    seedlotCollectionMethod.setConeCollectionMethodDescription("Climbing.");
    seedlotCollectionMethod.setAuditInformation(new AuditInformation("user1"));

    repository.saveAndFlush(seedlotCollectionMethod);
  }
}
