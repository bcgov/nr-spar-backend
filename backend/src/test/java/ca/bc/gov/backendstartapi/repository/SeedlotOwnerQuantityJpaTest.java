package ca.bc.gov.backendstartapi.repository;

import ca.bc.gov.backendstartapi.entity.embeddable.AuditInformation;
import ca.bc.gov.backendstartapi.entity.seedlot.SeedlotOwnerQuantity;
import ca.bc.gov.backendstartapi.enums.PaymentMethodEnum;
import ca.bc.gov.backendstartapi.enums.SeedlotStatusEnum;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class SeedlotOwnerQuantityJpaTest extends SeedlotEntityJpaTest {

  private final SeedlotOwnerQuantityRepository repository;

  @Autowired
  SeedlotOwnerQuantityJpaTest(
      SeedlotRepository seedlotRepository,
      SeedlotOwnerQuantityRepository seedlotOwnerQuantityRepository) {
    super(seedlotRepository);
    repository = seedlotOwnerQuantityRepository;
  }

  @Test
  void create() {
    var seedlot = createSeedlot("00000", SeedlotStatusEnum.SUB);
    var seedlotOwnerQuantity = new SeedlotOwnerQuantity(seedlot, "00020", "21");
    seedlotOwnerQuantity.setOriginalPercentageOwned(new BigDecimal(0));
    seedlotOwnerQuantity.setOriginalPercentageReserved(new BigDecimal(0));
    seedlotOwnerQuantity.setOriginalPercentageSurplus(new BigDecimal(0));
    seedlotOwnerQuantity.setMethodOfPaymentCode(PaymentMethodEnum.CSH);
    seedlotOwnerQuantity.setFundingSourceCode("ABC");
    seedlotOwnerQuantity.setAuditInformation(new AuditInformation("user1"));

    var savedSeedlotOwnerQuantity = repository.saveAndFlush(seedlotOwnerQuantity);
  }
}
