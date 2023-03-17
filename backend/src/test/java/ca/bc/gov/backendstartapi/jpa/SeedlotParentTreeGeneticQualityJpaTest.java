package ca.bc.gov.backendstartapi.jpa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.bc.gov.backendstartapi.entity.SeedlotParentTree;
import ca.bc.gov.backendstartapi.entity.SeedlotParentTreeGeneticQuality;
import ca.bc.gov.backendstartapi.entity.embeddable.AuditInformation;
import ca.bc.gov.backendstartapi.entity.idclass.SeedlotParentTreeGeneticQualityId;
import ca.bc.gov.backendstartapi.entity.idclass.SeedlotParentTreeId;
import ca.bc.gov.backendstartapi.enums.GeneticWorthEnum;
import ca.bc.gov.backendstartapi.enums.SeedlotStatusEnum;
import ca.bc.gov.backendstartapi.repository.SeedlotParentTreeGeneticQualityRepository;
import ca.bc.gov.backendstartapi.repository.SeedlotParentTreeRepository;
import ca.bc.gov.backendstartapi.repository.SeedlotRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SeedlotParentTreeGeneticQualityJpaTest extends SeedlotEntityJpaTest {

  private final SeedlotParentTreeRepository seedlotParentTreeRepository;

  private final SeedlotParentTreeGeneticQualityRepository repository;

  @Autowired
  SeedlotParentTreeGeneticQualityJpaTest(
      SeedlotRepository seedlotRepository,
      SeedlotParentTreeRepository seedlotParentTreeRepository,
      SeedlotParentTreeGeneticQualityRepository seedlotParentTreeGeneticQualityRepository) {
    super(seedlotRepository);
    this.seedlotParentTreeRepository = seedlotParentTreeRepository;
    repository = seedlotParentTreeGeneticQualityRepository;
  }

  @Test
  void create() {
    var seedlot = createSeedlot("00000", SeedlotStatusEnum.SUB);
    var seedlotParentTree =
        new SeedlotParentTree(
            seedlot, 1, new BigDecimal(10), new BigDecimal(10), new AuditInformation("user1"));
    seedlotParentTree.setSmpSuccessPercentage(1);
    seedlotParentTree.setNonOrchardPollenContaminationCount(1);

    var savedSeedlotParentTree = seedlotParentTreeRepository.save(seedlotParentTree);

    var seedlotParentTreeGeneticQuality =
        new SeedlotParentTreeGeneticQuality(
            seedlotParentTree,
            "GC",
            GeneticWorthEnum.AD,
            new BigDecimal(10),
            new AuditInformation("user1"));
    seedlotParentTreeGeneticQuality.setQualityValueEstimated(true);

    repository.saveAndFlush(seedlotParentTreeGeneticQuality);

    var savedSeedlotParentTreeGeneticQuality =
        repository.findById(
            new SeedlotParentTreeGeneticQualityId(
                new SeedlotParentTreeId(seedlot.getId(), savedSeedlotParentTree.getParentTreeId()),
                seedlotParentTreeGeneticQuality.getGeneticTypeCode(),
                seedlotParentTreeGeneticQuality.getGeneticWorthCode()));
    assertTrue(savedSeedlotParentTreeGeneticQuality.isPresent());
  }
}
