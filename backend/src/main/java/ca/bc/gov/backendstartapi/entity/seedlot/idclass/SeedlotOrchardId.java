package ca.bc.gov.backendstartapi.entity.seedlot.idclass;

import ca.bc.gov.backendstartapi.entity.seedlot.Seedlot;
import java.io.Serial;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** Composite key for {@link ca.bc.gov.backendstartapi.entity.seedlot.SeedlotOrchard}. */
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SeedlotOrchardId implements Serializable {

  @Serial private static final long serialVersionUID = 5760853750403068516L;

  @NonNull private Seedlot seedlot;

  @NonNull private String orchard;
}
