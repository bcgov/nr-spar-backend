package ca.bc.gov.backendstartapi.entity;

import ca.bc.gov.backendstartapi.entity.embeddable.AuditInformation;
import ca.bc.gov.backendstartapi.entity.idclass.SmpMixGeneticQualityId;
import ca.bc.gov.backendstartapi.enums.GeneticWorthEnum;
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

/** The calculated Genetic Worth value(s) for the Supplemental Mass Pollination mix. */
@Entity
@Table(name = "smp_mix_gen_qlty")
@IdClass(SmpMixGeneticQualityId.class)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor
@Getter
@Setter
public class SmpMixGeneticQuality {

  // region Identifier
  @Id
  @JoinColumn(name = "seedlot_number", referencedColumnName = "seedlot_number")
  @JoinColumn(name = "parent_tree_id", referencedColumnName = "parent_tree_id")
  @ManyToOne
  @NonNull
  private SmpMix smpMix;

  @Id
  @Column(name = "genetic_type_code", length = 2, nullable = false)
  @NonNull
  private String geneticTypeCode;

  @Id
  @Column(name = "genetic_worth_code", length = 3, nullable = false)
  @Enumerated(value = EnumType.STRING)
  @NonNull
  private GeneticWorthEnum geneticWorthCode;
  // endregion

  @Column(name = "genetic_quality_value", precision = 4, scale = 1, nullable = false)
  @NonNull
  private BigDecimal geneticQualityValue;

  @Column(name = "estimated_ind", nullable = false)
  @NonNull
  public boolean qualityValueEstimated;

  @Embedded @NonNull private AuditInformation auditInformation;

  @Column(name = "revision_count", nullable = false)
  @Version
  @Setter(AccessLevel.NONE)
  private int revisionCount;
}
