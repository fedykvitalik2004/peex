package org.vitalii.fedyk.peex.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.vitalii.fedyk.peex.MathematicsOperations.*;

@DisplayName("Grouped mathematical operations")
public class MathematicsOperationsTest {
  @Nested
  class RandomNumberTests {

    @Test
    void generateIntegerRandomNumber_whenGivenMinAndMax_thenReturnsNumberWithinRange() {
      int min = 5;
      int max = 15;
      int result = generateIntegerRandomNumber(min, max);
      assertTrue(result >= min && result <= max);
    }
  }

  @Nested
  @DisplayName("Exponential and Logarithmic Function Tests")
  class ExponentialLogTests {

    @Test
    void calculateExponential_whenInputIsOne_thenReturnsE() {
      assertEquals(Math.E, calculateExponential(1), 1e-10);
    }

    @Test
    void calculateLogarithm10_whenInputIsTen_thenReturnsOne() {
      assertEquals(1.0, calculateLogarithm10(10), 1e-10);
    }
  }
}
