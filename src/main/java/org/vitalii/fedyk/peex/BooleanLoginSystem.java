package org.vitalii.fedyk.peex;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BooleanLoginSystem {
    private boolean isRegistered;
    private boolean isPasswordCorrect;

    public void checkLogin() {
        if (isRegistered && isPasswordCorrect) {
            System.out.println("You are logged in");
        } else if (isRegistered && !isPasswordCorrect) {
            System.out.println("Your password is incorrect");
        } else if (!isRegistered){
            System.out.println("You are not registered.");
        }
    }

    public static void main(String[] args) {
        BooleanLoginSystem booleanLoginSystem = new BooleanLoginSystem(true, false);
        System.out.println("Case 1");
        booleanLoginSystem.checkLogin();
        booleanLoginSystem = new BooleanLoginSystem(true, true);
        System.out.println("Case 2");
        booleanLoginSystem.checkLogin();
        booleanLoginSystem = new BooleanLoginSystem(false, false);
        System.out.println("Case 3");
        booleanLoginSystem.checkLogin();

    }
}
