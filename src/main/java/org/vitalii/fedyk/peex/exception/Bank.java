package org.vitalii.fedyk.peex.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Bank {
    public final Map<String, Double> users = new HashMap<>();

    public void addSum(final String userId, final double sum) {
        if (sum <= 0 ) {
            throw new OperationNotPermittedException(LocalDateTime.now(), "Sum is less or equal to 0");
        }
        users.merge(userId, sum, (Double::sum));
    }

    public Double get(final String userId) {
        return users.get(userId);
    }

    public boolean isUserExists(final String userId) {
        return users.containsKey(userId);
    }

    public void withdraw(final String userId, final double sum) {
        if (sum <= 0 ) {
            throw new OperationNotPermittedException(LocalDateTime.now(), "Sum is less or equal to 0. You cannot withdraw");
        } else if (!isUserExists(userId)) {
            throw new OperationNotPermittedException(LocalDateTime.now(), "The user with such %s id doesn't exist"
                    .formatted(userId));
        }
        final double currentSum = users.get(userId);
        final double rest = currentSum - sum;
        if (rest < 0) {
            throw new OperationNotPermittedException(LocalDateTime.now(), "The sum you want to withdraw is greater than " +
                                                                          "available. Your current amount is " + currentSum);
        }
        users.put(userId, rest);
    }

    public static void main(String[] args) {
        final Bank bank = new Bank();

        final String userId = UUID.randomUUID().toString();
        bank.addSum(userId, 45);

        System.out.println("User has " + bank.get(userId));

        try {
            bank.withdraw(userId, 1000);
        } catch (final OperationNotPermittedException e) {
            System.out.println(e.getDateTime() + " - " + e.getMessage());
        }
    }
}
