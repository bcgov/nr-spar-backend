package ca.bc.gov.backendstartapi.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/** This enumeration represents a payment method code. */
@Schema(description = "This object represents a payment method code.")
public enum PaymentMethodEnum implements DescribedEnum {
  CLA("Invoice to MOF Client Account"),
  CSH("Cash Sale"),
  ITC("Invoice to Client Address"),
  ITO("Invoice to Other Address"),
  JV("Journal Voucher"),
  NC("Non-chargeable");

  private final String description;

  PaymentMethodEnum(String description) {
    this.description = description;
  }

  @Override
  public String description() {
    return description;
  }
}
