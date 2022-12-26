package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.FavoriteActivity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteActivityRepository
    extends JpaRepository<FavoriteActivity, Long> {

  List<FavoriteActivity> findAllByUserId(Long userId);
}
