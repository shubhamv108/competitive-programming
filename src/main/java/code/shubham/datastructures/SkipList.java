package code.shubham.datastructures;

public class SkipList<V extends Comparable<V>> {

    public class Node<V extends Comparable<V>> {
        private final V value;
        private Node<V> next;
        private final Node<V> down;
        public Node(V value, Node<V> next, Node<V> down) {
            this.value = value;
            this.next = next;
            this.down = down;
        }
    }

    private Node<V> head = new Node(-1, null, null);

    public SkipList() {

    }

    public boolean search(V target) {
        Node<V> current = head;
        while (current != null) {
            while (current.next != null && current.next.value.compareTo(target) < 0)
                current = current.next;
            if (current.next != null && current.next.value.compareTo(target) == 0)
                return true;
            current = current.down;
        }
        return false;
    }

    public void add(V val) {
        java.util.LinkedList<Node<V>> stack = new java.util.LinkedList<>();
        Node<V> current = head;
        while (current != null) {
            while (current.next != null && current.next.value.compareTo(val) < 0)
                current = current.next;
            stack.push(current);
            current = current.down;
        }

        boolean insert = true;
        Node<V> down = null;
        while (insert && !stack.isEmpty()) {
            current = stack.removeLast();
            down = current.next = new Node(val, current.next, down);
            insert = isHead();
        }
        if (insert)
            head = new Node(-1, null, head);
    }

    boolean isHead() {
        return (System.nanoTime() & 1) == 1;

    }

    public boolean delete(V val) {
        boolean isFound = false;
        Node<V> current = head;
        while (current != null) {
            while (current.next != null && current.next.value.compareTo(val) < 0)
                current = current.next;
            if (current.next != null && current.next.value.compareTo(val) == 0) {
                if (!isFound)
                    isFound = true;
                current.next = current.next.next;
            }
            current = current.down;
        }
        return isFound;
    }

}

