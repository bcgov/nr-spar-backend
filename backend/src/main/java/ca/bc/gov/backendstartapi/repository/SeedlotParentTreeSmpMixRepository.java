package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.SeedlotParentTreeSmpMix;
import ca.bc.gov.backendstartapi.entity.idclass.SeedlotParentTreeSmpMixId;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository for {@link SeedlotParentTreeSmpMix SeedlotParentTreeSMPMixes}. */
public interface SeedlotParentTreeSmpMixRepository
    extends JpaRepository<SeedlotParentTreeSmpMix, SeedlotParentTreeSmpMixId> {}
