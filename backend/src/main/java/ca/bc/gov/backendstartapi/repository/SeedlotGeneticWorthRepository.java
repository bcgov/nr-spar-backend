package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.SeedlotGeneticWorth;
import ca.bc.gov.backendstartapi.entity.idclass.SeedlotGeneticWorthId;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository for {@link SeedlotGeneticWorth SeedlotGeneticWorth}. */
public interface SeedlotGeneticWorthRepository
    extends JpaRepository<SeedlotGeneticWorth, SeedlotGeneticWorthId> {}
