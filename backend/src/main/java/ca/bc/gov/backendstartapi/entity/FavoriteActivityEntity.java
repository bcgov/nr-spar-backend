package ca.bc.gov.backendstartapi.entity;

import ca.bc.gov.backendstartapi.exception.NotRemovableEntityException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a user's favorite activity.
 */
@Entity
@Getter
@Setter
@Table(name = "favorite_activity")
public class FavoriteActivityEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @JsonIgnore
  private UserEntity user;

  @Column(name = "activity_title", updatable = false)
  private String activityTitle;

  @Column
  private Boolean highlighted;

  @Column
  private Boolean enabled;

  public FavoriteActivityEntity() {
    this.highlighted = false;
    this.enabled = true;
  }

  @PreRemove
  private void preRemove() {
    throw new NotRemovableEntityException();
  }
}
