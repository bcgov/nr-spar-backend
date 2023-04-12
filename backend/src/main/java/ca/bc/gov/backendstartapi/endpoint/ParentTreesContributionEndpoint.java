package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.exception.CsvTableParsingException;
import ca.bc.gov.backendstartapi.service.parser.ConeAndPollenCountCsvTableParser;
import ca.bc.gov.backendstartapi.service.parser.SmpCalculationCsvTableParser;
import ca.bc.gov.backendstartapi.vo.parser.ConeAndPollenCount;
import ca.bc.gov.backendstartapi.vo.parser.SmpMixVolume;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/** Endpoints to receive CSV files for parsing. */
@RestController
@RequestMapping(path = "/api/seedlots", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@Tag(name = "Seedlot")
@RequiredArgsConstructor
public class ParentTreesContributionEndpoint {

  private final ConeAndPollenCountCsvTableParser contributionTableCsvParser;

  private final SmpCalculationCsvTableParser smpCalculationTableParser;

  /**
   * Parse the CSV table in {@code file} and return the data stored in it.
   *
   * @param seedlotNumber the seedlot to associate the results with (<b>note:</b> has no effect
   *     right now)
   * @param file a CSV file containing the table to be parsed
   * @return a list with the data that has been parsed
   * @throws IOException in case of problems while accessing {@code file}'s content
   */
  @Operation(
      summary = "Upload a file containing a CSV table to be parsed",
      description =
          "Upload a file with a CSV table and parse it, returning a list of the table's content.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A list with all the values parsed from the CSV table in `file`."),
        @ApiResponse(
            responseCode = "400",
            description = "Table doesn't match the format",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
      })
  @PostMapping(
      path = "/{seedlotNumber}/parent-trees-contribution/cone-pollen-count-table/upload",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<List<ConeAndPollenCount>> handleConeAndPollenCountTableUpload(
      @PathVariable("seedlotNumber")
          @Parameter(
              description = "The number of the seedlot to which the data in the file refers to")
          String seedlotNumber,
      @RequestParam("file")
          @Parameter(description = "The text file to be uploaded. It must contain a CSV table")
          MultipartFile file)
      throws IOException {
    try {
      // TODO: validate the information against the seedlot's orchard
      //   All trees on the table must belong to the seedlot's orchard.
      return ResponseEntity.ok(contributionTableCsvParser.parse(getFileResource(file)));
    } catch (CsvTableParsingException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    }
  }

  /**
   * Parse CSV table in {@code file} and return the data stored in it.
   *
   * @param seedlotNumber the seedlot to associate the results with (<b>note:</b> has no effect
   *     right now)
   * @param file a CSV file containing the table to be parsed
   * @return a list with the data that has been parsed
   * @throws IOException in case of problems while accessing {@code file}'s content
   */
  @Operation(
      summary = "Upload a file containing a CSV table to be parsed",
      description =
          "Upload a file with a CSV table and parse it, returning a list of the table's content.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A list with all the values parsed from the CSV table in `file`."),
        @ApiResponse(
            responseCode = "400",
            description = "Table doesn't match the format",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
      })
  @PostMapping(
      path = "/{seedlotNumber}/parent-trees-contribution/smp-calculation-table/upload",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<List<SmpMixVolume>> handleSmpCalculationTableUpload(
      @PathVariable("seedlotNumber")
          @Parameter(
              description = "The number of the seedlot to which the data in the file refers to")
          String seedlotNumber,
      @RequestParam("file")
          @Parameter(description = "The text file to be uploaded. It must contain a CSV table")
          MultipartFile file)
      throws IOException {
    try {
      /*
       * TODO:
       *   We must have the breeding values of all the trees (even those that don't belong to the
       *   seedlot's orchard).
       */
      return ResponseEntity.ok(smpCalculationTableParser.parse(getFileResource(file)));
    } catch (CsvTableParsingException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    }
  }

  private Resource getFileResource(MultipartFile file) {
    var resource = file.getResource();
    var filename = resource.getFilename();
    if (filename == null || !filename.substring(filename.lastIndexOf('.')).equals(".csv")) {
      throw new CsvTableParsingException("CSV files only");
    }
    return resource;
  }
}
