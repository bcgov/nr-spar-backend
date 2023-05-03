package ca.bc.gov.backendstartapi.entity.seedlot;

import ca.bc.gov.backendstartapi.entity.embeddable.AuditInformation;
import ca.bc.gov.backendstartapi.enums.GeneticClassEnum;
import ca.bc.gov.backendstartapi.enums.SeedlotSourceEnum;
import ca.bc.gov.backendstartapi.enums.SeedlotStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** A registered seedlot. */
@Entity
@Table(name = "seedlot")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter
@Setter
public class Seedlot implements Serializable {

  @Id
  @Column(name = "seedlot_number", length = 5)
  @NonNull
  private String id;

  @Column(name = "seedlot_status_code", length = 3, nullable = false)
  @Enumerated(EnumType.STRING)
  @NonNull
  private SeedlotStatusEnum status;

  @Column(name = "seedlot_comment", length = 2000)
  private String comment;

  // region Applicant
  @Column(name = "applicant_client_number", length = 8)
  private String applicantClientNumber;

  @Column(name = "applicant_locn_code", length = 2)
  private String applicantLocationCode;

  @Column(name = "applicant_email_address", length = 100)
  private String applicantEmailAddress;
  // endregion

  // region Lot information
  @Column(name = "vegetation_code", length = 8)
  private String vegetationCode;

  @Column(name = "genetic_class_code", length = 2)
  @Enumerated(EnumType.STRING)
  private GeneticClassEnum geneticClassCode;

  @Column(name = "seedlot_source_code", length = 3)
  @Enumerated(EnumType.STRING)
  private SeedlotSourceEnum seedlotSourceCode;

  @Column(name = "to_be_registrd_ind")
  private Boolean intendedForCrownLand;

  @Column(name = "bc_source_ind")
  private Boolean sourceInBc;
  // endregion

  // region Collection
  @Column(name = "collection_client_number", length = 8)
  private String collectionClientNumber;

  @Column(name = "collection_locn_code", length = 2)
  private String collectionLocationCode;

  @Column(name = "collection_start_date")
  private LocalDateTime collectionStartDate;

  @Column(name = "collection_end_date")
  private LocalDateTime collectionEndDate;

  @Column(name = "no_of_containers", precision = 6, scale = 2)
  private BigDecimal numberOfContainers;

  @Column(name = "vol_per_container", precision = 6, scale = 2)
  private BigDecimal containerVolume;

  /**
   * Usually, but not necessarily, the product of {@link #numberOfContainers} and {@link
   * #containerVolume}.
   */
  @Column(name = "clctn_volume", precision = 6, scale = 2)
  private BigDecimal totalConeVolume;
  // endregion

  // region Interim storage
  @Column(name = "interm_strg_client_number", length = 8)
  private String interimStorageClientNumber;

  @Column(name = "interm_strg_locn_code", length = 2)
  private String interimStorageLocationCode;

  @Column(name = "interm_strg_st_date")
  private LocalDateTime interimStorageStartDate;

  @Column(name = "interm_strg_end_date")
  private LocalDateTime interimStorageEndDate;

  @Column(name = "interm_facility_code", length = 3)
  private String interimStorageFacilityCode;
  // endregion

  // region Orchard
  @Column(name = "female_gametic_mthd_code", length = 4)
  private String femaleGameticContributionMethod;

  @Column(name = "male_gametic_mthd_code", length = 4)
  private String maleGameticContributionMethod;

  @Column(name = "controlled_cross_ind")
  private Boolean producedThroughControlledCross;

  @Column(name = "biotech_processes_ind")
  private Boolean producedWithBiotechnologicalProcesses;

  @Column(name = "pollen_contamination_ind")
  private Boolean pollenContaminationPresentInOrchard;

  @Column(name = "pollen_contamination_pct")
  private Integer pollenContaminationPercentage;

  @Column(name = "contaminant_pollen_bv", precision = 4, scale = 1)
  private BigDecimal pollenContaminantBreedingValue;

  @Column(name = "pollen_contamination_mthd_code", length = 4)
  private String pollenContaminationMethodCode;
  // endregion

  // region Parent tree & SMP
  @Column(name = "total_parent_trees")
  private Integer totalParentTrees;

  // SMP: Supplemental mass pollination.
  @Column(name = "smp_success_pct")
  private Integer smpSuccessPercentage;

  @Column(name = "effective_pop_size", precision = 5, scale = 1)
  private BigDecimal effectivePopulationSize;

  @Column(name = "tested_parent_tree_cont_pct", precision = 6, scale = 2)
  private BigDecimal testedParentTreeContributionPercentage;

  @Column(name = "coancestry", precision = 20, scale = 10)
  private BigDecimal coancestry;

  @Column(name = "smp_parents_outside")
  private Integer parentsOutsideTheOrchardUsedInSmp;

  @Column(name = "non_orchard_pollen_contam_pct")
  private Integer nonOrchardPollenContaminationPercentage;
  // endregion

  // region Extraction & Storage
  @Column(name = "extractory_client_number", length = 8)
  private String extractionClientNumber;

  @Column(name = "extractory_locn_code", length = 2)
  private String extractionLocationCode;

  @Column(name = "extraction_st_date")
  private LocalDateTime extractionStartDate;

  @Column(name = "extraction_end_date")
  private LocalDateTime extractionEndDate;

  @Column(name = "storage_client_number", length = 8)
  private String storageClientNumber;

  @Column(name = "storage_locn_code", length = 2)
  private String storageLocationCode;

  @Column(name = "temporary_storage_start_date")
  private LocalDateTime temporaryStorageStartDate;

  @Column(name = "temporary_storage_end_date")
  private LocalDateTime temporaryStorageEndDate;
  // endregion

  // region Legal & Audit
  // "I hereby declare that the information provided is true and correct..."
  @Column(name = "declared_userid", length = 30)
  private String declarationOfTrueInformationUserId;

  @Column(name = "declared_timestamp")
  private LocalDateTime declarationOfTrueInformationTimestamp;

  @Embedded private AuditInformation auditInformation;

  @Column(name = "revision_count", nullable = false)
  @Version
  @Setter(AccessLevel.NONE)
  private int revisionCount;
  // endregion
}
