package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.UserEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/** This class contains the methods to retrieve and store users into the database. */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

  List<UserEntity> findAllByEmail(String email);
}
