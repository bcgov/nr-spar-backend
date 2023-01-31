package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.UserProfileEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/** This class contains the methods to retrieve and store users into the database. */
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, String> {

  Optional<UserProfileEntity> findByUserId(String userId);
}
