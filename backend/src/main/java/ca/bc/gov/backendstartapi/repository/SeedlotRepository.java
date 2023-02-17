package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.seedlot.Seedlot;
import org.springframework.data.jpa.repository.JpaRepository;

/** The repository for {@link Seedlot Seedlots}. */
public interface SeedlotRepository extends JpaRepository<Seedlot, String> {}
