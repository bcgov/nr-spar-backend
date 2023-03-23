package ca.bc.gov.backendstartapi.entity.idclass;

import ca.bc.gov.backendstartapi.entity.SmpMixGeneticQuality;
import ca.bc.gov.backendstartapi.enums.GeneticWorthEnum;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** Composite key for {@link SmpMixGeneticQuality}. */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SmpMixGeneticQualityId implements Serializable {

  @NonNull private SmpMixId smpMix;

  @NonNull private String geneticTypeCode;

  @NonNull private GeneticWorthEnum geneticWorthCode;
}
