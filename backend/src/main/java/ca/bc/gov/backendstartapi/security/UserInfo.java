package ca.bc.gov.backendstartapi.security;

import java.util.Set;

/** This record represents a user logged and his information from JWT token. */
public record UserInfo(
    String name,
    String email,
    Set<String> roles,
    String identityProvider,
    String idirUsername,
    String businessbceidUsername,
    Boolean emailVerified,
    String givenName,
    String displayName,
    String familyName) {}
