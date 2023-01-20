package ca.bc.gov.backendstartapi.entity;

import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import ca.bc.gov.backendstartapi.exception.NotRemovableEntityException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

/** This class represents a user's favorite activity in the database. */
@Entity
@Getter
@Setter
@With
@AllArgsConstructor
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
  @Enumerated(value = EnumType.STRING)
  private ActivityEnum activityTitle;

  @Column private Boolean highlighted;

  @Column private Boolean enabled;

  public FavoriteActivityEntity() {
    this.highlighted = false;
    this.enabled = true;
  }

  @PreRemove
  private void preRemove() {
    throw new NotRemovableEntityException();
  }
}
