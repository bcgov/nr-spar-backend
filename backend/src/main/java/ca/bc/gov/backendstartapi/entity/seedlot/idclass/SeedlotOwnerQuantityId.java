package ca.bc.gov.backendstartapi.entity.seedlot.idclass;

import ca.bc.gov.backendstartapi.entity.seedlot.Seedlot;
import java.io.Serial;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** Composite key for {@link ca.bc.gov.backendstartapi.entity.seedlot.SeedlotOwnerQuantity}. */
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SeedlotOwnerQuantityId implements Serializable {

  @Generated @Serial private static final long serialVersionUID = 6774130750081444732L;

  @NonNull private Seedlot seedlot;

  @NonNull private String ownerClientNumber;

  @NonNull private String ownerLocationCode;
}
