package ca.bc.gov.backendstartapi.endpoint;

import ca.bc.gov.backendstartapi.enums.PaymentMethodEnum;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PaymentMethodEndpointTest extends DescribedEnumEndpointTest<PaymentMethodEnum> {

  PaymentMethodEndpointTest(WebApplicationContext webApplicationContext) {
    super(webApplicationContext);
    enumClass = PaymentMethodEnum.class;
    endpointPrefix = "/api/payment-methods";
  }
}
