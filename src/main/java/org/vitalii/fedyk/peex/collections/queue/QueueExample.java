package org.vitalii.fedyk.peex.collections.queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        //FIFO
        final Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            //O(1)
            System.out.println("Added " + queue.offer(i));
        }

        System.out.println("Checked item " + queue.element());

        while (!queue.isEmpty()) {
            System.out.println("Removed " + queue.remove());
        }
    }
}
