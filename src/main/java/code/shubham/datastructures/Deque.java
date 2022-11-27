package code.shubham.datastructures;

public class Deque<T> {

    private Node head = null;
    private Node tail = null;

    public void addFirst(T n) {
        Node temp = new Node(n);
        temp.next = head;
        if (head != null) head.prev = temp;
        head = temp;
        if (tail == null) tail = head;
    }

    public void addLast(T n) {
        Node temp = new Node(n);
        if (tail == null) {
            tail = head = temp;
        } else {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
    }

    public void removeFirst() {
        if (head == null) return;
        if (head.next == null) {
            head = tail = null;
        } else {
            head.next.prev = null;
            head = head.next;
        }
    }

    public void removeLast() {
        if (tail == null) return;
        if (tail.prev == null) {
            tail = null;
            head = null;
        }
        else {
            tail.prev.next = null;
            tail = tail.prev;
        }
    }

    public T peekFirst() {
        if (head == null) return null;
        return head.val;
    }

    public T peekLast() {
        if (tail == null) return null;
        return tail.val;
    }

    public boolean isEmpty() {
        return head == null;
    }

    void print() {
        Node temp = head;
        while (temp != null) {
            System.out.printf("%d ", temp.val);
            temp = temp.next;
        };
    }



    class Node {
        T val;
        Node next;
        Node prev;
        Node (T val) {
            this.val = val;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node temp = head;
        while (temp != null) {
            sb.append(head.val).append(',');
            temp = temp.next;
        }
        return sb.append(" ]").toString();
    }

    public static void main(String[] args) {
        Deque q = new Deque();
        q.addFirst(1);
        q.addLast(1);
        q.removeFirst();
        q.removeLast();
        q.addLast(1);
        q.print();
    }


}
