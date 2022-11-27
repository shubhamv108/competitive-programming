package code.shubham.collections;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue {

    private Queue<Object> queue = new LinkedList<>();
    private int capacity = 10;

    private ReentrantLock putLock = new ReentrantLock();

    public BoundedBlockingQueue(int size) {
        this.capacity = size;
    }

    private void enqueue(Object e) {
        try {
            this.putLock.lock();
            while (queue.size() == capacity) await();
            if (queue.size() == 0) notifyAll();
            this.queue.add(e);
        } finally {
            putLock.unlock();
        }
    }

    private Object dequeue()  {
        putLock.lock();
        try {
            while (queue.size() == 0) await();
            if (queue.size() == capacity) notifyAll();
            return this.queue.poll();
        } finally {
            putLock.unlock();
        }
    }

    private void await () {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Thread Interrupted", e);
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
