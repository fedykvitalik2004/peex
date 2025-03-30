package org.vitalii.fedyk.peex.collections.list;

import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            System.out.println("Pushed " + i);
            stack.push(i);
        }

        System.out.println("Popped twice: " + stack.pop() + " and " + stack.pop());
        System.out.println("Peeked twice: " + stack.peek() + " and " + stack.peek());
    }
}
