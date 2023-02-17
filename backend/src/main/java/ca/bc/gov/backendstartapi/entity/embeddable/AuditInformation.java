package ca.bc.gov.backendstartapi.entity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** Used to track the changes made in a registry. */
@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class AuditInformation implements Serializable {

  /** User that entered the registry in the system. */
  @NonNull
  @Column(name = "entry_userid", length = 30, nullable = false)
  private String entryUserId;

  @NonNull
  @Column(name = "entry_timestamp", nullable = false)
  private LocalDateTime entryTimestamp;

  /** User who last updated this registry. */
  @NonNull
  @Column(name = "update_userid", length = 30, nullable = false)
  private String updateUserId;

  @NonNull
  @Column(name = "update_timestamp", nullable = false)
  private LocalDateTime updateTimestamp;

  @Column(name = "revision_count", nullable = false)
  private int revisionCount;
}
