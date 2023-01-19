package ca.bc.gov.backendstartapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** This class represents a system User in the database. */
@Entity
@Getter
@Setter
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String email;

  @Column(name = "last_login")
  private LocalDateTime lastLogin;

  @OneToMany(mappedBy = "user")
  private Set<FavoriteActivityEntity> favoriteActivities;
}
