package ca.bc.gov.backendstartapi.security;

import java.util.Set;

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
