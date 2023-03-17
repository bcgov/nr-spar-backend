package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.SeedlotParentTree;
import ca.bc.gov.backendstartapi.entity.idclass.SeedlotParentTreeId;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository for {@link SeedlotParentTree SeedlotParentTrees}. */
public interface SeedlotParentTreeRepository
    extends JpaRepository<SeedlotParentTree, SeedlotParentTreeId> {}
