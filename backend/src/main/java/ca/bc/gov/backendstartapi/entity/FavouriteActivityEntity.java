package ca.bc.gov.backendstartapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Schema(description = "An object representing a user's favourite activity in the database")
public class FavouriteActivityEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "The ID of the entity in the database, also the PK.", example = "42")
  private Long id;

  @Column(name = "user_id")
  @Schema(
      description = "The user ID. This value comes from the JWT Token, from the 'sub' claim.",
      example = "d1c0c0d1fab34a19816bb0a506ab705b@idir")
  private String userId;

  @Column(name = "activity", updatable = false)
  @Schema(
      description = "An activity or a page name the user can access or favourite",
      example = "My Seedlots")
  private String activity;

  @Column
  @Schema(
      description = "Defines if the favourite activity is highlighted on the dashboard",
      example = "false")
  private Boolean highlighted;

  @Column
  @Schema(
      description = "Defines if the favourite activity is enabled on the dashboard",
      example = "true")
  private Boolean enabled;

  public FavouriteActivityEntity() {
    this.highlighted = false;
    this.enabled = true;
  }
}
