package org.vitalii.fedyk.peex;

import java.math.BigDecimal;
import java.util.Objects;

record Money(BigDecimal amount) {
    private static int quantity;

    Money {
        Objects.requireNonNull(amount);
        quantity++;
    }

//    //allowed
//    public Money(BigDecimal amount) {
//        this.amount = amount;
//    }

    public Money() {
        this(BigDecimal.ONE);
    }

    public static Money getEmpty() {
        quantity += 1;
        return new Money(BigDecimal.ZERO);
    }

    public static int getQuantity() {
        return quantity;
    }
}

public class RecordUsage {
    public static void main(String[] args) {
        final Money money = Money.getEmpty();
        System.out.println(Money.getQuantity());

        try {
            new Money(null);
        } catch (NullPointerException e) {
            System.out.println("It can't be null");
        }
    }
}
