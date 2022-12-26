package ca.bc.gov.backendstartapi.security;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

  private String name;
  private String email;
  private Set<String> roles;

  private String identityProvider;
  private String idirUsername;
  private String businessbceidUsername;
  private Boolean emailVerified;
  private String givenName;
  private String displayName;
  private String familyName;
}
