package ca.bc.gov.backendstartapi.entity.seedlot;

import ca.bc.gov.backendstartapi.entity.embeddable.AuditInformation;
import ca.bc.gov.backendstartapi.entity.seedlot.idclass.SeedlotOwnerQuantityId;
import ca.bc.gov.backendstartapi.enums.PaymentMethodEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** Relational entity between a {@link Seedlot} and a forest client. */
@Entity
@Table(name = "seedlot_owner_quantity")
@IdClass(SeedlotOwnerQuantityId.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter
@Setter
public class SeedlotOwnerQuantity {

  // region Identifier
  @Id
  @JoinColumn(name = "seedlot_number")
  @ManyToOne
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
  @Enumerated(EnumType.STRING)
  private PaymentMethodEnum methodOfPaymentCode;

  @Column(name = "spar_fund_srce_code", length = 3)
  private String fundingSourceCode;

  @Embedded private AuditInformation auditInformation;

  @Column(name = "revision_count", nullable = false)
  @Version
  @Setter(AccessLevel.NONE)
  private int revisionCount;
}
