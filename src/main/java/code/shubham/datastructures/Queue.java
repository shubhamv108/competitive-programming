package code.shubham.datastructures;

class QueueNode<T> {
    T t;
    QueueNode<T> next;
    QueueNode(T t) {
        this.t = t;
    }
}

public class Queue<T> {

    QueueNode<T> first;
    QueueNode<T> cur;

    void offer(T t) {
        QueueNode<T> n = new QueueNode<T>(t);
        if (cur == null) {
            cur = n;
            first = cur;
        } else {
            cur.next = n;
            cur = cur.next;
        }
    }

    T poll() {
        if (isEmpty())
            return null;
        T t = first.t;
        first = first.next;
        return t;
    }

    boolean isEmpty() {
        return first == null;
    }

}
