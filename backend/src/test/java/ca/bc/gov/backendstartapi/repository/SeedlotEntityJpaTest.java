package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.embeddable.AuditInformation;
import ca.bc.gov.backendstartapi.entity.seedlot.Seedlot;
import ca.bc.gov.backendstartapi.enums.GeneticClassEnum;
import ca.bc.gov.backendstartapi.enums.SeedlotSourceEnum;
import ca.bc.gov.backendstartapi.enums.SeedlotStatusEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

class SeedlotEntityJpaTest {

  protected final SeedlotRepository repository;

  @Autowired
  protected SeedlotEntityJpaTest(SeedlotRepository seedlotRepository) {
    repository = seedlotRepository;
  }

  protected Seedlot createSeedlot(String id, SeedlotStatusEnum status) {
    var seedlot = new Seedlot(id, status);

    seedlot.setComment("A seedlot.");
    seedlot.setApplicantClientNumber("00000001");
    seedlot.setApplicantLocationCode("02");
    seedlot.setApplicantEmailAddress("applicant@email.com");

    seedlot.setVegetationCode("VEG");
    seedlot.setGeneticClassCode(GeneticClassEnum.A);
    seedlot.setSeedlotSourceCode(SeedlotSourceEnum.CUS);
    seedlot.setIntendedForCrownLand(true);
    seedlot.setSourceInBc(true);

    seedlot.setCollectionClientNumber("00000003");
    seedlot.setCollectionLocationCode("04");
    seedlot.setCollectionStartDate(LocalDateTime.now());
    seedlot.setCollectionEndDate(LocalDateTime.now());
    seedlot.setNumberOfContainers(new BigDecimal(10));
    seedlot.setContainerVolume(new BigDecimal(20));
    seedlot.setTotalConeVolume(new BigDecimal(200));

    seedlot.setInterimStorageClientNumber("00000005");
    seedlot.setInterimStorageLocationCode("06");
    seedlot.setInterimStorageStartDate(LocalDateTime.now());
    seedlot.setInterimStorageEndDate(LocalDateTime.now());
    seedlot.setInterimStorageFacilityCode("007");

    seedlot.setFemaleGameticContributionMethod("F");
    seedlot.setMaleGameticContributionMethod("M");
    seedlot.setProducedThroughControlledCross(true);
    seedlot.setProducedWithBiotechnologicalProcesses(true);
    seedlot.setPollenContaminationPresentInOrchard(true);
    seedlot.setPollenContaminationPercentage(30);
    seedlot.setPollenContaminantBreedingValue(new BigDecimal(100));
    seedlot.setPollenContaminationMethodCode("P");

    seedlot.setTotalParentTrees(10);
    seedlot.setSmpSuccessPercentage(70);
    seedlot.setEffectivePopulationSize(new BigDecimal(300));
    seedlot.setTestedParentTreeContributionPercentage(new BigDecimal(80));
    seedlot.setCoancestry(new BigDecimal(30));
    seedlot.setParentsOutsideTheOrchardUsedInSmp(20);
    seedlot.setNonOrchardPollenContaminationPercentage(50);

    seedlot.setExtractionClientNumber("00000009");
    seedlot.setExtractionLocationCode("10");
    seedlot.setExtractionStartDate(LocalDateTime.now());
    seedlot.setExtractionEndDate(LocalDateTime.now());
    seedlot.setStorageClientNumber("00000011");
    seedlot.setStorageLocationCode("12");
    seedlot.setTemporaryStorageStartDate(LocalDateTime.now());
    seedlot.setTemporaryStorageEndDate(LocalDateTime.now());

    seedlot.setDeclarationOfTrueInformationUserId("user1");
    seedlot.setDeclarationOfTrueInformationTimestamp(LocalDateTime.now());
    seedlot.setAuditInformation(new AuditInformation("user1"));

    return repository.save(seedlot);
  }
}
