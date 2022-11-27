package code.shubham.datastructures;

/**
 * ToDo
 */
public class FrontMiddleBackQueue {

    class Node {
        int val;
        Node prev, next;
        Node() {}
        Node (int val) {
            this.val = val;
        }

        void insertNext(int val) {
            insertNext(new Node(val));
        }

        void insertNext(Node node) {
            node.prev = this;
            node.next = this.next;
            this.next.prev = node;
            this.next = node;
        }

        Node clear() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
            this.next = null;
            this.prev = null;
            return this;
        }
    }

    Node front = new Node(), mid = front, back = new Node();
    boolean isOddCount;

    public FrontMiddleBackQueue() {
        front.next = back;
        back.prev = front;
    }

    public void pushFront(int val) {
        front.insertNext(val);
        isOddCount = !isOddCount;

        mid = isOddCount ? mid.next : mid.prev;
    }

    public void pushMiddle(int val) {
        Node newNode =  new Node(val);
        if (isOddCount)
            mid.prev.insertNext(newNode);
        else
            mid.insertNext(newNode);

        mid = newNode;
        isOddCount = !isOddCount;
    }

    public void pushBack(int val) {
        back.prev.insertNext(val);
        isOddCount = !isOddCount;

        if (isOddCount)
            mid = mid.next;
    }

    public int popFront() {
        if (isEmpty())
            return -1;

        if (!isOddCount)
            mid = mid.next;

        if (isOddCount && front.next == mid)
            mid = front;

        isOddCount = !isOddCount;
        return front.next.clear().val;
    }

    public int popMiddle() {
        if (isEmpty())
            return -1;

        Node temp = mid;
        mid = isOddCount ? mid.prev : mid.next;
        isOddCount = !isOddCount;
        return temp.clear().val;
    }

    public int popBack() {
        if (isEmpty())
            return -1;

        if (isOddCount)
            mid = mid.prev;

        isOddCount = !isOddCount;
        return back.prev.clear().val;
    }

    private boolean isEmpty() {
        return front.next == back;
    }

    public static void main(String[] args) {
          FrontMiddleBackQueue q = new FrontMiddleBackQueue();
          q.pushFront(888438);
          q.pushFront(888438);
    }
}