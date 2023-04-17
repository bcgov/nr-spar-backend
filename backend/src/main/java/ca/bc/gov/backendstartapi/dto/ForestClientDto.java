package ca.bc.gov.backendstartapi.dto;

import ca.bc.gov.backendstartapi.enums.ForestClientStatusEnum;
import ca.bc.gov.backendstartapi.enums.ForestClientTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * A party that does business with the ministry.
 *
 * @param clientNumber the number that identifies this client
 * @param clientName the party's last name, if it's an individual; its legal name, otherwise
 * @param legalFirstName the party's first name, if it's an individual; null, otherwise
 * @param legalMiddleName the party's middle name, if it's an individual; null, otherwise
 * @param clientStatusCode the current status of this client
 * @param clientTypeCode the type of this client (individual, corporation, etc.)
 * @param acronym an acronym that uniquely identifies this client; can ce used as an alternative to
 *     the client number
 */
@Schema(description = "One of the many agencies that work with the ministry.")
public record ForestClientDto(
    @Schema(description = "An eight-digit number that identifies the client.") String clientNumber,
    @Schema(
            description =
                "The client last name if it's an individual or the company name if it's a company.")
        String clientName,
    @Schema(description = "The first name of the individual, or null if it's a company.")
        String legalFirstName,
    @Schema(description = "The middle name of the individual, or null if it's a company")
        String legalMiddleName,
    ForestClientStatusEnum clientStatusCode,
    ForestClientTypeEnum clientTypeCode,
    @Schema(description = "An acronym for this client; works as an alternative identifier.")
        String acronym)
    implements Serializable {}
