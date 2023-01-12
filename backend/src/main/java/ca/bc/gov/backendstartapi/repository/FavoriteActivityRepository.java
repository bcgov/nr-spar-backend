package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.FavoriteActivityEntity;
import ca.bc.gov.backendstartapi.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * This class represents a user's favorite activity repository.
 */
public interface FavoriteActivityRepository extends CrudRepository<FavoriteActivityEntity, Long> {

  List<FavoriteActivityEntity> findAllByUser(UserEntity user);

  Optional<FavoriteActivityEntity> findByActivityTitle(String activityTitle);
}
