package code.shubham.collections;

public class IntegerHashMap {

    private class Node {
        int key;
        int value;
        Node next;
        Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    private Node[] table = new Node[10000];
    private int size;

    /** Initialize your data structure here. */
    public IntegerHashMap() {

    }

    /** value will always be non-negative. */
    public void put(int key, int value) {

        int k = key%table.length;
        Node n = table[k];
        if (n == null) {
            table[k] = new Node(key, value);
            this.size++;
        } else {
            while (n != null) {
                if (n.key == key) {
                    n.value = value;
                    break;
                }
                if (n.next == null) {
                    n.next = new Node(key, value);
                    this.size++;
                    break;
                }
                n = n.next;
            }
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int k = key%table.length;
        Node n = table[k];
        while (n != null && n.key != key) {
            n = n.next;
        }
        return n == null ? -1 : n.value;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int k = key%table.length;
        Node n = table[k];
        if (n == null) return;
        if (n.key == key) {
            table[k] = table[k].next;
        } else {
            while (n.next != null && n.next.key != key) {
                n = n.next;
            }
            if (n.next != null) n.next = n.next.next;
        }
    }

    public static void main(String[] args) {
        IntegerHashMap map = new IntegerHashMap();
        map.put(65, 65);
        map.put(42, 0);
        map.remove(42);
        map.put(17, 90);
        map.put(50, 33);
        map.remove(50);
    }

}
