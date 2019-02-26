package code.collections;

import java.util.LinkedList;
import java.util.Queue;

public class BoundedBlockingQueue {


    private Queue<Object> queue = new LinkedList<>();
    private int capacity = 10;


    public BoundedBlockingQueue (int size) {
        this.capacity = size;
    }

    private synchronized void enqueue (Object e) {
        while (queue.size() == capacity) await();
        if (queue.size() == 0) notifyAll();
        this.queue.add(e);
    }

    private synchronized Object dequeue () {
        while (queue.size() == 0) await();
        if (queue.size() == capacity) notifyAll();
        return this.queue.poll();
    }

    private void await () {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("", e);
        }
    }

    public void delay () {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

}
