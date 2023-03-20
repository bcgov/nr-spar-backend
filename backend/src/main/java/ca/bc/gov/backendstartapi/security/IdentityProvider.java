package ca.bc.gov.backendstartapi.security;

import java.util.Arrays;
import java.util.Optional;
import org.springframework.security.oauth2.jwt.Jwt;

/** Enumeration of the identity providers our application works with. */
enum IdentityProvider {
  IDIR("idir"),
  BUSINESS_BCEID("bceidbusiness");

  private final String claimName;

  IdentityProvider(String claimName) {
    this.claimName = claimName;
  }

  /**
   * Extract the identity provider from a {@link Jwt}.
   *
   * @param token a JSON web token to extract the provider from
   * @return the identity provider, if one is found
   */
  public static Optional<IdentityProvider> fromClaim(Jwt token) {
    return Arrays.stream(values())
        .filter(provider -> provider.claimName.equals(token.getClaimAsString("identity_provider")))
        .findFirst();
  }
}
