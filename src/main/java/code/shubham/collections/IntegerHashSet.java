package code.shubham.collections;

public class IntegerHashSet {

    IntegerHashMap hashMap = new IntegerHashMap();

    /** Initialize your data structure here. */
    public IntegerHashSet() {

    }

    public void add(int key) {
        hashMap.put(key);
    }

    public void remove(int key) {
        hashMap.remove(key);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return hashMap.containsKey(key);
    }

    private class IntegerHashMap {

        private class Node {
            int key;
            Boolean value;
            Node next;

            Node(int k, Boolean v) {
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
        public void put(int key) {
            int k = key%table.length;
            Node n = table[k];
            if (n == null) {
                table[k] = new Node(key, null);
                this.size++;
            } else {
                while (n != null) {
                    if (n.key == key) {
                        break;
                    }
                    if (n.next == null) {
                        n.next = new Node(key, null);
                        this.size++;
                        break;
                    }
                    n = n.next;
                }
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public boolean containsKey(int key) {
            int k = key % table.length;
            Node n = table[k];
            while (n != null && n.key != key) {
                n = n.next;
            }
            return n == null ? false : true;
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

    }
}