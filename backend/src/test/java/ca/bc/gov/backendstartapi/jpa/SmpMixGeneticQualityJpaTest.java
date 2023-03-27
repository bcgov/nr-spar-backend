package ca.bc.gov.backendstartapi.jpa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.bc.gov.backendstartapi.entity.SmpMix;
import ca.bc.gov.backendstartapi.entity.SmpMixGeneticQuality;
import ca.bc.gov.backendstartapi.entity.embeddable.AuditInformation;
import ca.bc.gov.backendstartapi.entity.idclass.SmpMixGeneticQualityId;
import ca.bc.gov.backendstartapi.entity.idclass.SmpMixId;
import ca.bc.gov.backendstartapi.enums.GeneticWorthEnum;
import ca.bc.gov.backendstartapi.enums.SeedlotStatusEnum;
import ca.bc.gov.backendstartapi.repository.SeedlotRepository;
import ca.bc.gov.backendstartapi.repository.SmpMixGeneticQualityRepository;
import ca.bc.gov.backendstartapi.repository.SmpMixRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SmpMixGeneticQualityJpaTest extends SeedlotEntityJpaTest {

  private final SmpMixRepository smpMixRepository;

  private final SmpMixGeneticQualityRepository repository;

  @Autowired
  SmpMixGeneticQualityJpaTest(
      SeedlotRepository seedlotRepository,
      SmpMixRepository smpMixRepository,
      SmpMixGeneticQualityRepository smpMixGeneticQualityRepository) {
    super(seedlotRepository);
    this.smpMixRepository = smpMixRepository;
    repository = smpMixGeneticQualityRepository;
  }

  @Test
  void create() {
    var seedlot = createSeedlot("00000", SeedlotStatusEnum.SUB);
    var smpMix = new SmpMix(seedlot, 1, 1, new AuditInformation("user1"));
    smpMix.setProportion(new BigDecimal(10));

    var savedSmpMix = smpMixRepository.save(smpMix);

    var smpMixGeneticQuality =
        new SmpMixGeneticQuality(
            smpMix,
            "GC",
            GeneticWorthEnum.AD,
            new BigDecimal(10),
            true,
            new AuditInformation("user1"));

    repository.saveAndFlush(smpMixGeneticQuality);

    var savedSmpMixGeneticQuality =
        repository.findById(
            new SmpMixGeneticQualityId(
                new SmpMixId(seedlot.getId(), savedSmpMix.getParentTreeId()),
                smpMixGeneticQuality.getGeneticTypeCode(),
                smpMixGeneticQuality.getGeneticWorthCode()));
    assertTrue(savedSmpMixGeneticQuality.isPresent());
  }
}
