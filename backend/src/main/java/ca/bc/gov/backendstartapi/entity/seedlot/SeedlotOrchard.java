package ca.bc.gov.backendstartapi.entity.seedlot;

import ca.bc.gov.backendstartapi.entity.embeddable.AuditInformation;
import ca.bc.gov.backendstartapi.entity.seedlot.idclass.SeedlotOrchardId;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.io.Serial;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** Relational entity between a {@link Seedlot} and an orchard. */
@Entity
@Table(name = "seedlot_orchard")
@IdClass(SeedlotOrchardId.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter
@Setter
public class SeedlotOrchard implements Serializable {

  @Generated @Serial private static final long serialVersionUID = -3202392558489728268L;

  // region Identifier
  @Id
  @ManyToOne(optional = false)
  @JoinColumn(name = "seedlot_number", nullable = false)
  @NonNull
  private Seedlot seedlot;

  @Id
  @Column(name = "orchard_id", length = 3, nullable = false)
  @NonNull
  private String orchard;
  // endregion

  @Column(name = "primary_ind", nullable = false)
  private boolean primary;

  @Embedded private AuditInformation auditInformation;

  @Column(name = "revision_count", nullable = false)
  @Version
  private int revisionCount;
}
