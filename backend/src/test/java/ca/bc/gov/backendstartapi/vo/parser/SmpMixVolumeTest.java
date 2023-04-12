package ca.bc.gov.backendstartapi.vo.parser;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

class SmpMixVolumeTest {

  @Test
  void parentTreeNumberMustBePositive() {
    assertThrowsExactly(IllegalArgumentException.class, () -> new SmpMixVolume(0, 1d));
  }

  @Test
  void pollenVolumeMustBeNonNegative() {
    assertThrowsExactly(IllegalArgumentException.class, () -> new SmpMixVolume(1, -1d));
  }
}
