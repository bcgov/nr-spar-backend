package ca.bc.gov.backendstartapi.entity;

import ca.bc.gov.backendstartapi.enums.ActivityEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

/** This class represents a user's favorite activity in the database. */
@Entity
@Getter
@Setter
@With
@AllArgsConstructor
@Table(name = "favourite_activity")
public class FavouriteActivityEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "activity", updatable = false)
  @Enumerated(value = EnumType.STRING)
  private ActivityEnum activity;

  @Column private Boolean highlighted;

  @Column private Boolean enabled;

  public FavouriteActivityEntity() {
    this.highlighted = false;
    this.enabled = true;
  }
}
