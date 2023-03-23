package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.SmpMixGeneticQuality;
import ca.bc.gov.backendstartapi.entity.idclass.SmpMixGeneticQualityId;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository for {@link SmpMixGeneticQuality SMPMixGeneticQualities}. */
public interface SmpMixGeneticQualityRepository
    extends JpaRepository<SmpMixGeneticQuality, SmpMixGeneticQualityId> {}
