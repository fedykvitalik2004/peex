package org.vitalii.fedyk.peex.innerclasses;

import lombok.AllArgsConstructor;
import org.vitalii.fedyk.peex.Concatenator;

@AllArgsConstructor
class Outer {
    private String firstName;
    private String lastName;

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
}

public class InnerClassUsage {
    public static void main(String[] args) {
        final Outer outer = new Outer("Vitalii", "Fedyk");
        System.out.println("Full name is " + outer.concatenatedString());
        Outer.ReverseDisplaying reverseDisplaying = outer.new ReverseDisplaying();
        System.out.println("Reversed string is " + reverseDisplaying.reverse());
    }
}
