package ca.bc.gov.backendstartapi.enums.parser;

/** Headers for the <i>SMP calculation</i> table. */
public enum SmpMixHeader implements CsvParsingHeader {
  PARENT_TREE_NUMBER("Parent tree number"),
  POLLEN_VOLUME_ML("Pollen volume (ml)");

  private final String displayName;

  SmpMixHeader(String displayName) {
    this.displayName = displayName;
  }

  @Override
  public String toString() {
    return displayName;
  }
}
