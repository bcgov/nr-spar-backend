package ca.bc.gov.backendstartapi.entity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;

/** Used to track the changes made in a registry. */
@Embeddable
@NoArgsConstructor
@Getter
public class AuditInformation implements Serializable {

  @Generated @Serial private static final long serialVersionUID = -4611969329464135377L;

  /** User that entered the registry in the system. */
  @Column(name = "entry_userid", length = 30, nullable = false, updatable = false)
  @CreatedBy
  private String entryUserId;

  @Column(name = "entry_timestamp", nullable = false, updatable = false)
  private LocalDateTime entryTimestamp;

  /** User who last updated this registry. */
  @NonNull
  @Setter
  @Column(name = "update_userid", length = 30, nullable = false)
  private String updateUserId;

  @Column(name = "update_timestamp", nullable = false)
  private LocalDateTime updateTimestamp;

  public AuditInformation(@NonNull String userId) {
    entryUserId = userId;
    updateUserId = userId;
  }

  @PrePersist
  private void prePersist() {
    entryTimestamp = LocalDateTime.now();
    updateTimestamp = entryTimestamp;
  }

  @PreUpdate
  private void preUpdate() {
    updateTimestamp = LocalDateTime.now();
  }
}
