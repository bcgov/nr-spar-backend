package ca.bc.gov.backendstartapi.jpa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.bc.gov.backendstartapi.entity.SeedlotParentTree;
import ca.bc.gov.backendstartapi.entity.embeddable.AuditInformation;
import ca.bc.gov.backendstartapi.entity.idclass.SeedlotParentTreeId;
import ca.bc.gov.backendstartapi.enums.SeedlotStatusEnum;
import ca.bc.gov.backendstartapi.repository.SeedlotParentTreeRepository;
import ca.bc.gov.backendstartapi.repository.SeedlotRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SeedlotParentTreeJpaTest extends SeedlotEntityJpaTest {

  private final SeedlotParentTreeRepository repository;

  @Autowired
  SeedlotParentTreeJpaTest(
      SeedlotRepository seedlotRepository,
      SeedlotParentTreeRepository seedlotParentTreeRepository) {
    super(seedlotRepository);
    this.repository = seedlotParentTreeRepository;
  }

  @Test
  void create() {
    var seedlot = createSeedlot("00000", SeedlotStatusEnum.SUB);
    var seedlotParentTree =
        new SeedlotParentTree(
            seedlot, 1, new BigDecimal(10), new BigDecimal(10), new AuditInformation("user1"));
    seedlotParentTree.setSmpSuccessPercentage(1);
    seedlotParentTree.setNonOrchardPollenContaminationCount(1);

    repository.saveAndFlush(seedlotParentTree);

    var savedSeedlotParentTree = repository.findById(new SeedlotParentTreeId(seedlot.getId(), 1));
    assertTrue(savedSeedlotParentTree.isPresent());
  }
}
