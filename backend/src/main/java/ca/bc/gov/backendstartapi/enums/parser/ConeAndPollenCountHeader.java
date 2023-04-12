package ca.bc.gov.backendstartapi.enums.parser;

/** Headers for the <i>Cone and pollen count</i> table. */
public enum ConeAndPollenCountHeader implements CsvParsingHeader {
  PARENT_TREE_NUMBER("Parent Tree number"),
  POLLEN_COUNT("Pollen count"),
  CONE_COUNT("Cone count"),
  SMP_SUCCESS("SMP success"),
  POLLEN_CONTAMINATION("Pollen contamination");

  private final String displayName;

  ConeAndPollenCountHeader(String displayName) {
    this.displayName = displayName;
  }

  @Override
  public String toString() {
    return displayName;
  }
}
