package ca.bc.gov.backendstartapi.entity.seedlot.idclass;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** Composite key for {@link ca.bc.gov.backendstartapi.entity.seedlot.SeedlotOrchard}. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SeedlotOrchardId implements Serializable {

  @NonNull private String seedlot;

  @NonNull private String orchard;
}
