package ca.bc.gov.backendstartapi.service.parser;

import ca.bc.gov.backendstartapi.enums.parser.ConeAndPollenCountHeader;
import ca.bc.gov.backendstartapi.vo.parser.ConeAndPollenCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Parser for Cone and pollen count tables. */
@Service
public class ConeAndPollenCountCsvTableParser
    extends CsvTableParser<ConeAndPollenCountHeader, ConeAndPollenCount> {

  /** Build an instance with the appropriate header and row parsers. */
  @Autowired
  public ConeAndPollenCountCsvTableParser() {
    super(
        new CsvTableHeaderParser<>(ConeAndPollenCountHeader.class),
        new CsvTableRowParser<>(ConeAndPollenCount::fromMap));
  }
}
