package ca.bc.gov.backendstartapi.entity.seedlot;

import ca.bc.gov.backendstartapi.entity.embeddable.AuditInformation;
import ca.bc.gov.backendstartapi.entity.seedlot.idclass.SeedlotOwnerQuantityId;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** Relational entity between a {@link Seedlot} and a forest client. */
@Entity
@Table(name = "seedlot_owner_quantity")
@IdClass(SeedlotOwnerQuantityId.class)
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class SeedlotOwnerQuantity implements Serializable {

  @Serial private static final long serialVersionUID = 3502144543715103911L;

  // region Identifier
  @Id
  @ManyToOne(optional = false)
  @JoinColumn(name = "seedlot_number", nullable = false)
  @NonNull
  private Seedlot seedlot;

  @Id
  @Column(name = "owner_client_number", length = 8, nullable = false)
  @NonNull
  private String ownerClientNumber;

  @Id
  @Column(name = "owner_locn_code", length = 2, nullable = false)
  @NonNull
  private String ownerLocationCode;
  // endregion

  @Column(name = "original_pct_owned", precision = 4, scale = 1, nullable = false)
  private BigDecimal originalPercentageOwned;

  @Column(name = "original_pct_rsrvd", precision = 4, scale = 1, nullable = false)
  private BigDecimal originalPercentageReserved;

  @Column(name = "original_pct_srpls", precision = 4, scale = 1, nullable = false)
  private BigDecimal originalPercentageSurplus;

  @Column(name = "method_of_payment_code", length = 3)
  private String methodOfPaymentCode;

  @Column(name = "spar_fund_srce_code", length = 3)
  private String fundingSourceCode;

  @Embedded private AuditInformation auditInformation;
}
