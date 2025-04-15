package org.vitalii.fedyk.peex.innerclasses;

import lombok.AllArgsConstructor;
import org.vitalii.fedyk.peex.Concatenator;

public class InnerAndStaticNestedClassUsage {
    @AllArgsConstructor
    public static class Outer {
        private String firstName;
        private String lastName;

        private Outer(Builder builder) {
            firstName = builder.firstName;
            lastName = builder.lastName;
        }

        private String delimiter() {
            return Concatenator.Delimiter.ONE_WHITESPACE.getString();
        }

        public String concatenatedString() {
            return this.new Inner().concat();
        }

        @AllArgsConstructor
        private class Inner {
            private String concat() {
                return firstName + delimiter() + lastName;
            }
        }

        public class ReverseDisplaying {
            public String reverse() {
                final StringBuilder stringBuilder = new StringBuilder(new Inner().concat());
                return stringBuilder.reverse().toString();
            }
        }

        public static class Builder {
            private String firstName;
            private String lastName;

            private Builder() {
            }

            public static Builder builder() {
                return new Builder();
            }

            public Builder firstName(String val) {
                firstName = val;
                return this;
            }

            public Builder lastName(String val) {
                lastName = val;
                return this;
            }

            public Outer build() {
                return new Outer(this);
            }
        }
    }

    public static void main(String[] args) {
        final Outer outer = Outer.Builder.builder()
                .firstName("Vitalii")
                .lastName("Fedyk")
                .build();
        System.out.println("Full name is " + outer.concatenatedString());
        Outer.ReverseDisplaying reverseDisplaying = outer.new ReverseDisplaying();
        System.out.println("Reversed string is " + reverseDisplaying.reverse());
    }
}
