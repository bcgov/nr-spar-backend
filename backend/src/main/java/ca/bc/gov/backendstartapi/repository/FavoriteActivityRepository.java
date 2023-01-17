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

  List<FavoriteActivityEntity> findAllByEnabledAndUser(Boolean enabled, Long userId);

  Optional<FavoriteActivityEntity> findByActivityTitle(String activityTitle);
}
