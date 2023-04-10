package ca.bc.gov.backendstartapi.vo.parser;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

class ConeAndPollenCountTest {

  @Test
  void parentTreeNumberMustBePositive() {
    assertThrowsExactly(
        IllegalArgumentException.class, () -> new ConeAndPollenCount(0, 1d, 1d, 1, 1));
  }

  @Test
  void coneCountMustBeNonNegative() {
    assertThrowsExactly(
        IllegalArgumentException.class, () -> new ConeAndPollenCount(1, -1d, 1d, 1, 1));
  }

  @Test
  void pollenCountMustBeNonNegative() {
    assertThrowsExactly(
        IllegalArgumentException.class, () -> new ConeAndPollenCount(1, 1d, -1d, 1, 1));
  }

  @Test
  void smpSuccessMustBeNonNegative() {
    assertThrowsExactly(
        IllegalArgumentException.class, () -> new ConeAndPollenCount(1, 1d, 1d, -1, 1));
  }

  @Test
  void pollenContaminationMustBeNonNegative() {
    assertThrowsExactly(
        IllegalArgumentException.class, () -> new ConeAndPollenCount(1, 1d, 1d, 1, -1));
  }
}
