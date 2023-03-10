package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.seedlot.SeedlotOwnerQuantity;
import ca.bc.gov.backendstartapi.entity.seedlot.idclass.SeedlotOwnerQuantityId;
import org.springframework.data.jpa.repository.JpaRepository;

/** The repository for {@link SeedlotOwnerQuantity SeedlotOwnerQuantities}. */
public interface SeedlotOwnerQuantityRepository
    extends JpaRepository<SeedlotOwnerQuantity, SeedlotOwnerQuantityId> {}
