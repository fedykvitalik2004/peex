package org.vitalii.fedyk.peex.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.vitalii.fedyk.peex.innerclasses.InnerAndStaticNestedClassUsage;

class OuterBuilderTest {

  private InnerAndStaticNestedClassUsage.Outer.Builder builder;

  @BeforeEach
  void setUp() {
    builder = InnerAndStaticNestedClassUsage.Outer.Builder.builder();
  }

  @Test
  void testBuildOuterWithFirstAndLastName() {
    final InnerAndStaticNestedClassUsage.Outer outer = builder
            .firstName("Vitalii")
            .lastName("Fedyk")
            .build();

    assertNotNull(outer);
    assertEquals("Vitalii Fedyk", outer.concatenatedString());
  }

  @Test
  void testReverseDisplaying() {
    final InnerAndStaticNestedClassUsage.Outer outer = builder
            .firstName("Vitalii")
            .lastName("Fedyk")
            .build();

    final InnerAndStaticNestedClassUsage.Outer.ReverseDisplaying reverseDisplaying = outer.new ReverseDisplaying();
    String reversed = reverseDisplaying.reverse();

    assertEquals("kydeF iilatiV", reversed);
  }

  @Test
  void testEmptyFields() {
    final InnerAndStaticNestedClassUsage.Outer outer = builder.build();

    assertEquals("null null", outer.concatenatedString());

    final InnerAndStaticNestedClassUsage.Outer.ReverseDisplaying reverseDisplaying = outer.new ReverseDisplaying();
    assertEquals("llun llun", reverseDisplaying.reverse());
  }
}