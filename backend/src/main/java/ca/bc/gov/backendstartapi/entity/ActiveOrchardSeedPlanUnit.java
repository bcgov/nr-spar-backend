package ca.bc.gov.backendstartapi.entity;

import ca.bc.gov.backendstartapi.entity.idclass.ActiveOrchardSeedPlanUnitId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** Auxiliary entity connecting an Orchard and a Seed Plan Unit (SPU). */
@Entity
@Table(name = "active_orchard_spu")
@IdClass(ActiveOrchardSeedPlanUnitId.class)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor
@Getter
@Setter
@Schema(description = "An association between an orchard and a Seed Plan Unit (SPU).")
public class ActiveOrchardSeedPlanUnit {

  @Id
  @Column(name = "orchard_id", length = 3, nullable = false)
  @NonNull
  private String orchardId;

  @Id
  @Column(name = "seed_plan_unit_id", nullable = false)
  @NonNull
  private int seedPlanUnitId;

  @Column(name = "active_ind", nullable = false)
  @NonNull
  @Schema(description = "If this association is active.")
  private boolean active;

  @Column(name = "retired_ind", nullable = false)
  @NonNull
  @Schema(
      description =
          "If the orchard has been retired; if so, **it should not be used for new registries**.")
  private boolean retired;

  /**
   * If the orchard hasn't had a SPU assigned to it. If {@code true}, {@link #seedPlanUnitId}'s
   * value will most likely be {@code -1}.
   */
  @Column(name = "no_spu_ind", nullable = false)
  @NonNull
  @Schema(description = "If this orchard has never had a SPU assigned to it.")
  private boolean spuNotAssigned;
}
