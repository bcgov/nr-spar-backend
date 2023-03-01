package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.enums.PaymentMethodEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Endpoints to fetch {@link PaymentMethodEnum}. */
@RestController
@RequestMapping(path = "/api/payment-methods", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@Tag(name = "PaymentMethod")
public class PaymentMethodEndpoint implements DescribedEnumEndpoint<PaymentMethodEnum> {

  @Override
  public Class<PaymentMethodEnum> enumClass() {
    return PaymentMethodEnum.class;
  }
}
