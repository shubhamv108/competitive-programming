package code.shubham.collections;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ToDo
 *
 * @param <T>
 */
public class UnboundedBlockingQueue<T> {

    public Queue<T> queue = new LinkedList<>();

    public synchronized void enqueue (Object e) {

    }

    public synchronized Object dequeue () {
        return null;
}

}
