package org.vitalii.fedyk.peex;

import lombok.*;
import org.vitalii.fedyk.peex.exception.OperationNotPermittedException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class BankStorage {
    private static final Set<BankAccount> bankAccounts = new HashSet<>();

    public static void addAccount(final BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }

    public static Optional<BankAccount> getBankAccount(final UUID uuid) {
        return bankAccounts.stream().filter(o -> o.getUserId().equals(uuid))
                .findFirst();
    }
}

//Notifications
@AllArgsConstructor
class Notification {
    protected final LocalDateTime sendingTime;
}

class AmountNotification extends Notification {
    protected final double amount;

    public AmountNotification(LocalDateTime sendingTime, double amount) {
        super(sendingTime);
        this.amount = amount;
    }

    @Override
    public String toString() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        return formatter.format(sendingTime) + " - " + "You received " + amount;
    }
}

class AmountNotificationOfPremiumUser extends AmountNotification {
    private final FullName fullNameOfSender;

    public AmountNotificationOfPremiumUser(final LocalDateTime sendingTime, final double amount,
                                           final FullName fullNameOfSender) {
        super(sendingTime, amount);
        this.fullNameOfSender = fullNameOfSender;
    }

    @Override
    public String toString() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        return formatter.format(sendingTime) + " - " + fullNameOfSender + " sent you " + amount;
    }
}

//Related classes to a bank account
@AllArgsConstructor
@Builder
class FullName {
    private final String firstName;
    private final String lastName;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

enum BankAccountStatus {
    REGULAR, PREMIUM
}

@Getter
class BankAccount {
    private final UUID userId = UUID.randomUUID();
    @Setter
    private FullName fullName;
    @Setter(value = AccessLevel.PROTECTED)
    private double balance = 0;
    @Setter
    private BankAccountStatus status;
    private final List<Notification> notifications = new ArrayList<>();

    public BankAccount(final FullName fullName, final BankAccountStatus status) {
        this.fullName = fullName;
        this.status = status;
        BankStorage.addAccount(this);
    }

    public void addNotification(final Notification notification) {
        notifications.add(notification);
    }

    public void topUp(final double amount) {
        balance += amount;
    }

    public final void processTransaction(final UUID anotherUserId, final double amount) {
        final double currentAccountBalance = balance - amount;
        if (currentAccountBalance < 0) {
            throw new OperationNotPermittedException("You cannot transfer such amount of money, because this amount " +
                                                     "is greater than your balance");
        }
        final BankAccount anotherBankAccount = BankStorage.getBankAccount(anotherUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        balance = currentAccountBalance;
        anotherBankAccount.setBalance(anotherBankAccount.getBalance() + amount);

        switch (anotherBankAccount.status) {
            case REGULAR -> {
                final Notification notification = new AmountNotification(LocalDateTime.now(), amount);
                anotherBankAccount.addNotification(notification);
            }
            case PREMIUM -> {
                final Notification notification = new AmountNotificationOfPremiumUser(LocalDateTime.now(), amount, fullName);
                anotherBankAccount.addNotification(notification);
            }
        }
    }

    public void readNotifications() {
        notifications.forEach(System.out::println);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        BankAccount that = (BankAccount) object;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }
}

public class FinalUsage {
    public static void main(String[] args) {
        final BankAccount regularBankAccount = new BankAccount(
                FullName.builder()
                        .firstName("Rick")
                        .lastName("Novac")
                        .build(), BankAccountStatus.REGULAR
        );
        regularBankAccount.topUp(1000);
        final BankAccount premiumBankAccount = new BankAccount(
                FullName.builder()
                        .firstName("Melvin")
                        .lastName("Forbis")
                        .build(), BankAccountStatus.PREMIUM
        );

        System.out.println("Balance of regular user before the transaction is " + regularBankAccount.getBalance());
        System.out.println("Balance of premium user before the transaction is " + premiumBankAccount.getBalance());
        regularBankAccount.processTransaction(premiumBankAccount.getUserId(), 400);

        premiumBankAccount.readNotifications();

        System.out.println("\nBalance of regular user after the transaction is " + regularBankAccount.getBalance());
        System.out.println("Balance of premium user after the transaction is " + premiumBankAccount.getBalance());
    }
}
