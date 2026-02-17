package code.shubham.design.datastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AllOOneDataStructure {
    class AllOne {

        class Node {
            Set<String> keys = new HashSet<>();
            Node prev, next;

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

            void addKey(String key) {
                keys.add(key);
            }
        }

        Node head = new Node();
        Node tail = new Node();
        HashMap<String, Integer> f = new HashMap<>();
        HashMap<Integer, Node> m = new HashMap<>();

        public AllOne() {
            head.next = tail;
            tail.prev = head;
        }

        public void inc(String key) {
            int c = f.getOrDefault(key, 0);
            Node cur = m.getOrDefault(c, head);
            f.put(key, ++c);
            Node next = m.get(c);
            if (next == null)
                next = add(c, cur, cur.next);
            next.addKey(key);
            removeKeyFromNode(cur, key, c-1);
        }

        public void dec(String key) {
            Integer c = f.get(key);
            Node cur = m.get(c);
            if (c == 1) {
                f.remove(key);
                removeKeyFromNode(cur, key, c);
                return;
            }
            f.put(key, --c);
            Node prev = m.get(c);
            if (prev == null)
                prev = add(c, cur.prev, cur);
            prev.addKey(key);
            removeKeyFromNode(cur, key, c+1);
        }

        Node add(int f, Node prev, Node next) {
            Node node = new Node();
            node.add(prev, next);
            m.put(f, node);
            return node;
        }

        void removeKeyFromNode(Node node, String key, int f) {
            if (node == head || node == tail)
                return;
            node.keys.remove(key);
            if (node.keys.isEmpty()) {
                node.remove();
                m.remove(f);
            }
        }

        public String getMaxKey() {
            return tail.prev.keys.stream().findFirst().orElse("");
        }

        public String getMinKey() {
            return head.next.keys.stream().findFirst().orElse("");
        }
    }

    public static void main(String[] args) {
        AllOOneDataStructure allOOneDataStructure = new AllOOneDataStructure();
        AllOne allOne = allOOneDataStructure.new AllOne();
        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("c");
        allOne.inc("c");
        allOne.inc("c");
        allOne.dec("b");
        allOne.dec("b");
        System.out.println(allOne.getMinKey());
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
