package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findUserByEmail(String email);
}
