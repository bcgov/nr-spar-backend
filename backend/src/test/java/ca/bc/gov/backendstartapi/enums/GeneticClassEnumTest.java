package ca.bc.gov.backendstartapi.enums;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GeneticClassEnumTest {

  @Test
  @DisplayName("getByCodeTest")
  void getByCodeTest() {
    Optional<GeneticClassEnum> geneticClassA = GeneticClassEnum.getByCode('A');
    Assertions.assertFalse(geneticClassA.isEmpty());
    Assertions.assertEquals(geneticClassA.get(), GeneticClassEnum.A_CLASS);

    Optional<GeneticClassEnum> geneticClassB = GeneticClassEnum.getByCode('B');
    Assertions.assertFalse(geneticClassB.isEmpty());
    Assertions.assertEquals(geneticClassB.get(), GeneticClassEnum.B_CLASS);

    Optional<GeneticClassEnum> emptyClass = GeneticClassEnum.getByCode('C');
    Assertions.assertTrue(emptyClass.isEmpty());
  }
}
