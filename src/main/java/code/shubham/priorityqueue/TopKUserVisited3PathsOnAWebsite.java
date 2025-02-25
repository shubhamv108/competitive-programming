package code.shubham.priorityqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TopKUserVisited3PathsOnAWebsite {

    class Solution {

        /**
         * given alist of User to the website tpath they visit. find the topN 3 paths visited by all users
         *
         * U1 /
         * U1 subscribers
         * U1 export
         * U1 filters
         * U2 /
         * U2 subscribers
         * U2 export
         * U2 filter
         * U3 catalog
         * U3 fg
         *
         * When k = 2
         * result: [/ subscribers export, subscribers export filter]
         */

        class Node {
            Map<String, Node> next = new ConcurrentHashMap<>();
            AtomicInteger freq = new AtomicInteger();

            Node parent;

            Node(Node parent) {
                this.parent = parent;
            }

            Node add(String word) {
                Node n = next.computeIfAbsent(word, e -> new Node(this));
                n.freq.incrementAndGet();
                return n;
            }

            public Integer getFreq() {
                return freq.get();
            }

            @Override
            public boolean equals(Object object) {
                if (this == object) return true;
                if (object == null || getClass() != object.getClass()) return false;
                Node node = (Node) object;
                return Objects.equals(next, node.next) && Objects.equals(freq, node.freq) && Objects.equals(parent, node.parent);
            }

            @Override
            public int hashCode() {
                return Objects.hash(next, freq, parent);
            }
        }

        // user -> [{subpath, freq} -> {subpath, freq} -> {subpath, freq}]
        Map<String, Node> userCurPath = new ConcurrentHashMap<>();
        Node root = new Node(null);
        Map<Integer, Set<Node>> freqToPaths = new ConcurrentHashMap<>();

        public void record(String[][] userPaths) {
            for (String[] path : userPaths) {
                Node n = userCurPath.computeIfAbsent(path[0], _ -> root).add(path[1]);
                userCurPath.put(path[0], n.add(path[1]));
                freqToPaths.getOrDefault(n.getFreq() - 1, Collections.emptySet()).remove(n);
            }
        }

        public ArrayList<String[]> top(int k) {
            HashMap<String, Integer> m = new HashMap<>();
return null;

        }



    }

}
