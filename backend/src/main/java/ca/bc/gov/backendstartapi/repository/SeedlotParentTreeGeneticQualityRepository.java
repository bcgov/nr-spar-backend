package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.SeedlotParentTreeGeneticQuality;
import ca.bc.gov.backendstartapi.entity.idclass.SeedlotParentTreeGeneticQualityId;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository for {@link SeedlotParentTreeGeneticQuality SeedlotParentTreeGeneticQualities}. */
public interface SeedlotParentTreeGeneticQualityRepository
    extends JpaRepository<SeedlotParentTreeGeneticQuality, SeedlotParentTreeGeneticQualityId> {}
