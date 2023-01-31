package ca.bc.gov.backendstartapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** This class represents a system User in the database. */
@Entity
@Getter
@Setter
@Table(name = "user_profile")
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileEntity {

  @Id
  @Column(name = "user_id")
  private String userId;

  @Column(name = "dark_theme")
  private Boolean darkTheme;
}
