package ca.bc.gov.backendstartapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** This class contains base configuration for Swagger API documentation. */
@Configuration
public class SwaggerConfig {

  private static final String DESCRIPTION =
      "A REST API to store new SPAR information into Postgres database.";
  private static final String TERMS_OF_SERVICE =
      "https://www2.gov.bc.ca/gov/content/data/open-data/api-terms-of-use-for-ogl-information";
  private static final String LICENSE_URL =
      "https://www2.gov.bc.ca/gov/content/data/open-data/open-government-licence-bc";

  /**
   * Creates an {@link OpenAPI} with all needed and related information.
   *
   * @return An {@link OpenAPI} instance
   */
  @Bean
  public OpenAPI theRestApi() {
    Info info = new Info();
    info.setTitle("SPAR Postgres REST API");
    info.setDescription(DESCRIPTION);
    info.setVersion("0.0.1");
    info.setTermsOfService(TERMS_OF_SERVICE);

    Contact contact = new Contact();
    contact.setName("Team Encora");
    contact.setEmail("michelle.douville@gov.bc.ca");
    contact.setUrl("https://github.com/orgs/bcgov/teams/encora");
    info.setContact(contact);

    License license = new License();
    license.setName("OGL-BC");
    license.setUrl(LICENSE_URL);
    info.setLicense(license);

    ExternalDocumentation externalDoc = new ExternalDocumentation();
    externalDoc.setDescription("Encora Team Jira Board");
    externalDoc.setUrl("https://apps.nrs.gov.bc.ca/int/jira/projects/FSADT2");

    SecurityScheme securityScheme = new SecurityScheme();
    securityScheme.setType(Type.HTTP);
    securityScheme.setScheme("bearer");
    securityScheme.setBearerFormat("JWT");

    Components components = new Components();
    components.addSecuritySchemes("bearerAuth", securityScheme);

    OpenAPI openApi = new OpenAPI();
    openApi.setInfo(info);
    openApi.setExternalDocs(externalDoc);
    openApi.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    openApi.setComponents(components);

    // TODO: find a way to keep this closer to the controllers themselves.
    Paths paths = new Paths();
    createSeedlotStatusPathItem(paths);
    createGeneticClassPathItem(paths);
    createLatitudeCodePathItem(paths);
    createLongitudeCodePathItem(paths);
    createGeneticWorthPathItem(paths);
    createMaleFemaleMethodologyPathItem(paths);
    createPaymentMethodPathItem(paths);
    createSeedlotSourcePathItem(paths);
    openApi.setPaths(paths);
    return openApi;
  }

  private void createSeedlotStatusPathItem(Paths paths) {
    paths.addPathItem(
        "/api/seedlot-status",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchAllSeedlotStatus")
                    .summary("Fetch all the possible seed lot status")
                    .description(
                        "Fetch all the possible status for a seed lot and their descriptions.")));

    paths.addPathItem(
        "/api/seedlot-status/{code}",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchSeedlotStatus")
                    .summary("Fetch a single seed lot status")
                    .description("Fetch a seed lot status by its code.")));
  }

  private void createGeneticClassPathItem(Paths paths) {
    paths.addPathItem(
        "/api/genetic-classes",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchAllGeneticClasses")
                    .summary("Fetch all the genetic classes")
                    .description(
                        "Fetch all the possible genetic class for a seedlot registration.")));

    paths.addPathItem(
        "/api/genetic-classes/{code}",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchGeneticClass")
                    .summary("Fetch a single genetic class")
                    .description("Fetch a genetic class by its code.")));
  }

  private void createLatitudeCodePathItem(Paths paths) {
    paths.addPathItem(
        "/api/latitude-codes",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchAllLatitudeCodes")
                    .summary("Fetch all latitude codes")
                    .description("Fetch all the possible latitude codes and their descriptions.")));

    paths.addPathItem(
        "/api/latitude-codes/{code}",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchLatitudeCode")
                    .summary("Fetch a single latitude code")
                    .description("Fetch a latitude code by its code.")));
  }

  private void createLongitudeCodePathItem(Paths paths) {
    paths.addPathItem(
        "/api/longitude-codes",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchAllLongitudeCodes")
                    .summary("Fetch all longitude codes")
                    .description(
                        "Fetch all the possible longitude codes and their descriptions.")));

    paths.addPathItem(
        "/api/longitude-codes/{code}",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchLongitudeCode")
                    .summary("Fetch a single longitude code")
                    .description("Fetch a longitude code by its code.")));
  }

  private void createGeneticWorthPathItem(Paths paths) {
    paths.addPathItem(
        "/api/genetic-worths",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchAllGeneticWorthCodes")
                    .summary("Fetch all genetic worth codes")
                    .description(
                        "Fetch all the possible genetic worth codes and their descriptions.")));

    paths.addPathItem(
        "/api/genetic-worths/{code}",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchGeneticWorthCode")
                    .summary("Fetch a single genetic worth code")
                    .description("Fetch a genetic worth code by its code.")));
  }

  private void createMaleFemaleMethodologyPathItem(Paths paths) {
    paths.addPathItem(
        "/api/male-female-methodologies",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchAllMaleFemaleMethodologyCodes")
                    .summary("Fetch all male/female methodology codes")
                    .description(
                        """
                        Fetch all the possible male/female methodology codes and their descriptions.
                        """)));

    paths.addPathItem(
        "/api/male-female-methodologies/{code}",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchMaleFemaleMethodologyCode")
                    .summary("Fetch a single male/female methodology code")
                    .description("Fetch a male/female methodology code by its code.")));
  }

  private void createPaymentMethodPathItem(Paths paths) {
    paths.addPathItem(
        "/api/payment-methods",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchAllPaymentMethodCodes")
                    .summary("Fetch all payment method codes")
                    .description(
                        "Fetch all the possible payment method codes and their descriptions.")));

    paths.addPathItem(
        "/api/payment-methods/{code}",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchPaymentMethodCode")
                    .summary("Fetch a single payment method code")
                    .description("Fetch a payment method code by its code.")));
  }

  private void createSeedlotSourcePathItem(Paths paths) {
    paths.addPathItem(
        "/api/seedlot-sources",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchAllSeedlotSourceCodes")
                    .summary("Fetch all seedlot source codes")
                    .description(
                        "Fetch all the possible seedlot source codes and their descriptions.")));

    paths.addPathItem(
        "/api/seedlot-sources/{code}",
        new PathItem()
            .get(
                new Operation()
                    .operationId("fetchSeedlotSourceCode")
                    .summary("Fetch a single seedlot source code")
                    .description("Fetch a seedlot source code by its code.")));
  }
}
