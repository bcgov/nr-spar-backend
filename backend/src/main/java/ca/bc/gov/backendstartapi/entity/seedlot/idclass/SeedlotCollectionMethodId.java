package ca.bc.gov.backendstartapi.entity.seedlot.idclass;

import ca.bc.gov.backendstartapi.entity.seedlot.Seedlot;
import java.io.Serial;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** Composite key for {@link ca.bc.gov.backendstartapi.entity.seedlot.SeedlotCollectionMethod}. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SeedlotCollectionMethodId implements Serializable {

  @Generated @Serial private static final long serialVersionUID = 2200282392247946290L;

  @NonNull private Seedlot seedlot;

  @NonNull private String coneCollectionMethodCode;
}