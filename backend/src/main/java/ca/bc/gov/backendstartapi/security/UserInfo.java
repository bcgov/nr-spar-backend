package ca.bc.gov.backendstartapi.security;

import java.util.Collections;
import java.util.Set;

/** This record represents a user logged and his information from JWT decoded token. To learn more
 * about the attributes and see reference list, you can see here:
 * <a href="https://github.com/bcgov/sso-keycloak/blob/dev/docs/migration-guide.md">IDP Attributes</a>
 */
public record UserInfo(
    String id,
    String firstName,
    String lastName,
    String email,
    String displayName,
    String idirUsername,
    String businessName,
    String identityProvider,
    Set<String> roles) {

  /**
   * Constructor only for ensuring immutability for the user's roles.
   *
   * @param id see the access method
   * @param firstName see the access method
   * @param lastName see the access method
   * @param email see the access method
   * @param displayName see the access method
   * @param idirUsername see the access method
   * @param businessName see the access method
   * @param identityProvider see the access method
   * @param roles see the access method
   */
  public UserInfo {
    roles = Collections.unmodifiableSet(roles);
  }

  /**
   * The user's ID. This prop comes from JWT 'sub' property and will exist when logged with both
   * IDIR and Business BCeID. E.g.: 123456789...45689@idir
   *
   * @return The user ID
   */
  @Override
  public String id() {
    return id;
  }

  /**
   * The user's first name. This prop comes from JWT 'given_name' claim and only exists when logged
   * with IDIR.
   *
   * @return The first name
   */
  @Override
  public String firstName() {
    return firstName;
  }

  /**
   * The user's last name. This prop comes from JWT 'family_name' claim and only exists when logged
   * with IDIR.
   *
   * @return The last name
   */
  @Override
  public String lastName() {
    return lastName;
  }

  /**
   * The user's email. This prop comes from JWT 'email' claim and exists for both IDIR and Business
   * BCeID.
   *
   * @return The email
   */
  @Override
  public String email() {
    return email;
  }

  /**
   * The name that should be displayed for this user. This prop comes from JWT 'display_name' claim
   * and exists for both IDIR and Business BCeID. E.g.: Potter, Harry LWRS:EX
   *
   * @return The display name
   */
  @Override
  public String displayName() {
    return displayName;
  }

  /**
   * The user's IDIR username containing eight characters. This prop comes from JWT 'idir_username'
   * claim and exists only when logged with IDIR. E.g.: HAPOTTER
   *
   * @return The IDIR username
   */
  @Override
  public String idirUsername() {
    return idirUsername;
  }

  /**
   * The business' name. This prop comes from JWT 'bceid_business_name' claim and exists only when
   * logged with Business BCeID.
   *
   * @return The business name
   */
  @Override
  public String businessName() {
    return businessName;
  }

  /**
   * The identity provider used to authenticate. This prop comes from JWT 'identity_provider' claim
   * and exists for both IDIR and Business BCeID.
   *
   * @return The identity provider. Possible values are: 'idir' or 'bceidbusiness'
   */
  @Override
  public String identityProvider() {
    return identityProvider;
  }

  /**
   * The user's roles. This prop comes from JWT 'client_roles' claim and exists only when the logged
   * user has one or more roles. It can occur for both IDIR and Business BCeID.
   *
   * @return The set of roles
   */
  @Override
  public Set<String> roles() {
    return roles;
  }
}
