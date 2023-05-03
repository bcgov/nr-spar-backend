package ca.bc.gov.backendstartapi.security;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import lombok.NonNull;

/**
 * This record represents a user logged and his information from a decoded JWT. To learn more about
 * the attributes and see reference list, you can see here: <a
 * href="https://github.com/bcgov/sso-keycloak/blob/dev/docs/migration-guide.md">IDP Attributes</a>.
 *
 * @param id the user's identifier. Read from the JWT {@code sub} property. E.g.: {@code
 *     123456789...45689@idir}
 * @param firstName the user's first name. Read from the JWT {@code given_name} claim. Present only
 *     when the identity is provided by {@link IdentityProvider#IDIR IDIR}
 * @param lastName The user's last name. Read from the JWT {@code family_name} claim. Present only
 *     when the identity is provided by {@code IDIR}
 * @param email the user's email. Read from the JWT {@code email} claim
 * @param displayName the name that should be displayed in the client for this user. Read from JWT
 *     {@code display_name} claim
 * @param idirUsername The user's IDIR username containing eight characters. This prop comes from
 *     the JWT {@code idir_username} claim. Present only when the identity is provided by {@code
 *     IDIR}. E.g.: {@code HAPOTTER}
 * @param businessName the business' name. This prop comes from the JWT {@code bceid_business_name}
 *     claim. Present only when the identity is provided by {@link IdentityProvider#BUSINESS_BCEID
 *     Business BCeID}
 * @param identityProvider the identity provider used to authenticate this user. This prop comes
 *     from the JWT {@code identity_provider} claim
 * @param roles The user's roles. This prop comes from the JWT {@code client_roles} claim
 */
public record UserInfo(
    @NonNull String id,
    String firstName,
    String lastName,
    @NonNull String email,
    @NonNull String displayName,
    String idirUsername,
    String businessName,
    @NonNull IdentityProvider identityProvider,
    @NonNull Set<String> roles) {

  /** Ensure immutability for the user's roles. */
  public UserInfo {
    switch (identityProvider) {
      case IDIR -> {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(idirUsername);
      }
      case BUSINESS_BCEID -> Objects.requireNonNull(businessName);
      default -> throw new IllegalArgumentException(
          "Not implemented for identity provider " + identityProvider);
    }
    roles = Collections.unmodifiableSet(roles);
  }
}
