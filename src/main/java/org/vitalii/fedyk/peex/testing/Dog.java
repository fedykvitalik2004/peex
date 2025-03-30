package org.vitalii.fedyk.peex.testing;

import org.springframework.stereotype.Component;

@Component
public class Dog {
    private final Toy toy;

    public Dog(Toy toy) {
        this.toy = toy;
    }

    public String getToyName() {
        return toy.getName();
    }

    public void sayHello() {
        toy.say("Hello");
    }
}
