package ca.bc.gov.backendstartapi.entity.idclass;

import ca.bc.gov.backendstartapi.enums.GeneticWorthEnum;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** Composite key for {@link ca.bc.gov.backendstartapi.entity.SeedlotParentTreeGeneticQuality}. */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SeedlotParentTreeGeneticQualityId implements Serializable {

  @NonNull private SeedlotParentTreeId seedlotParentTree;

  @NonNull private String geneticTypeCode;

  @NonNull private GeneticWorthEnum geneticWorthCode;
}
