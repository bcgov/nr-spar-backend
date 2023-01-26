package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.FavouriteActivityEntity;
import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/** This class represents a user's favorite activity repository. */
public interface FavouriteActivityRepository extends CrudRepository<FavouriteActivityEntity, Long> {

  List<FavouriteActivityEntity> findAllByUserId(String userId);

  List<FavouriteActivityEntity> findAllByEnabledAndUserId(Boolean enabled, String userId);

  Optional<FavouriteActivityEntity> findByActivity(ActivityEnum activity);
}
