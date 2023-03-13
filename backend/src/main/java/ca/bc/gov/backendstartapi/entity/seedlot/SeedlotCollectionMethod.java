package ca.bc.gov.backendstartapi.entity.seedlot;

import ca.bc.gov.backendstartapi.entity.embeddable.AuditInformation;
import ca.bc.gov.backendstartapi.entity.seedlot.idclass.SeedlotCollectionMethodId;
import ca.bc.gov.backendstartapi.enums.ConeCollectionMethodEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** The method used for the collection of a lot of seeds. */
@Entity
@Table(name = "seedlot_collection_method")
@IdClass(SeedlotCollectionMethodId.class)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor
@Getter
@Setter
public class SeedlotCollectionMethod {

  // region Identifier
  @Id
  @JoinColumn(name = "seedlot_number")
  @ManyToOne
  @NonNull
  private Seedlot seedlot;

  @Id
  @Column(name = "cone_collection_method_code", length = 30, nullable = false)
  @NonNull
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private String coneCollectionMethodCode;
  // endregion

  @Column(name = "cone_collection_method_desc", length = 400)
  private String coneCollectionMethodDescription;

  @Embedded private AuditInformation auditInformation;

  @Column(name = "revision_count", nullable = false)
  @Version
  private int revisionCount;

  public SeedlotCollectionMethod(
      @NonNull Seedlot seedlot, @NonNull ConeCollectionMethodEnum collectionMethod) {
    this.seedlot = seedlot;
    setConeCollectionMethodCode(collectionMethod);
  }

  public ConeCollectionMethodEnum getConeCollectionMethodCode() {
    return ConeCollectionMethodEnum.getByCode(coneCollectionMethodCode).orElseThrow();
  }

  public void setConeCollectionMethodCode(@NonNull ConeCollectionMethodEnum method) {
    coneCollectionMethodCode = method.getCode();
  }
}
