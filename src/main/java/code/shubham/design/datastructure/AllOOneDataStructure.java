package code.shubham.design.datastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class AllOOneDataStructure {
    class Solution1 {

        class Node {
            String k;
            int v;
            Node prev, next;

            Node(String k, int v) {
                this.k = k;
                this.v = v;
            }

            void remove() {
                this.prev.next = next;
                this.next.prev = this.prev;
                this.prev = null;
                this.next = null;
            }

            void add(Node prev, Node next) {
                this.next = next;
                this.prev = prev;
                this.prev.next = this;
                this.next.prev = this;
            }
        }

        Node head = new Node(null, -1);
        Node tail = new Node(null, -1);
        HashMap<String, Node> m = new HashMap<>();

        public Solution1() {
            head.next = tail;
            tail.prev = head;
        }

        public void inc(String key) {
            Node n = m.get(key);
            if (n == null) {
                n = new Node(key, 1);
                n.add(head, head.next);
                return;
            }
            n.v++;
            if (n.next == null)
                return;
            n.remove();
            if (n.v > tail.prev.v)
                n.add(tail.prev, tail);
            else
                n.add(tail.prev.prev, tail.prev);
        }

        public void dec(String key) {
            Node n = m.get(key);
            if (n == null)
                return;

            if (n.v == 1) {
                n.remove();
                m.remove(key);
                return;
            }

            n.v--;
            if (n.prev == head)
                return;
            n.remove();
            if (n.v < head.next.v)
                n.add(head, head.next);
            else
                n.add(head.next, head.next.next);
        }

        public String getMaxKey() {
            if (m.isEmpty())
                return "";
            return tail.prev.k;
        }

        public String getMinKey() {
            if (m.isEmpty())
                return "";
            return head.next.k;
        }
    }

    class Solution2 {
        HashMap<String, Integer> count = new HashMap<>();
        TreeMap<Integer, HashSet<String>> set = new TreeMap<>();

        public void inc(String key) {
            inc(key, count.getOrDefault(key, 0), 1);
        }

        public void dec(String key) {
            inc(key, count.get(key), -1);
        }

        private void inc(String key, int c, int by) {
            if (c > 0) {
                HashSet<String> contents = set.get(c);
                if (contents.size() == 1)
                    set.remove(c);
                else
                    contents.remove(key);
            }
            c += by;
            if (c == 0) {
                count.remove(key);
            } else {
                count.put(key, c);
                set.computeIfAbsent(c, k -> new HashSet<>()).add(key);
            }
        }

        public String getMaxKey() {
            if (set.isEmpty())
                return "";
            return set.lastEntry().getValue().stream().findFirst().get();
        }

        public String getMinKey() {
            if (set.isEmpty())
                return "";
            return set.firstEntry().getValue().stream().findFirst().get();
        }
    }

    public static void main(String[] args) {
        AllOOneDataStructure allOOneDataStructure = new AllOOneDataStructure();
        Solution2 allOne = allOOneDataStructure.new Solution2();
        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("c");
        allOne.inc("c");
        allOne.inc("c");
        allOne.dec("b");
        allOne.dec("b");
        System.out.println(allOne.getMinKey ());
        allOne.dec("a");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
}
