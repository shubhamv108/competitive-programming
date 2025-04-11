package code.shubham.priorityqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
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

    class Solution2 {
        public List<String> mostVisitedPattern(String[] U, int[] T, String[] W) {
            class Node implements Comparable<Node> {
                int i;
                Node (int i) { this.i = i; }
                public int compareTo(Node o) {
                    return T[i] - T[o.i];
                }
            }

            HashMap<String, TreeSet<Node>> v = new HashMap<>();
            for (int i = 0; i < U.length; ++i)
                v.computeIfAbsent(U[i], e -> new TreeSet<>()).add(new Node(i));

            Map<String, Integer> f = new HashMap<>();
            for (TreeSet<Node> a : v.values()) {
                LinkedList<Integer> w = new LinkedList<>();
                Iterator<Node> itr = a.iterator();
                for (int i = 0; i < 3; ++i)
                    w.offer(itr.next().i);

                f.merge(w.stream().map(i -> W[i] + "/").collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString(), 1, Integer::sum);
                while (itr.hasNext()) {
                    w.poll();
                    w.offer(itr.next().i);

                    f.merge(w.stream().map(i -> W[i] + "/").collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString(), 1, Integer::sum);
                }
            }

            int maxF = 0;
            String result = null;
            for (Map.Entry<String, Integer> e : f.entrySet())
                if (e.getValue() > maxF) {
                    maxF = e.getValue();
                    result = e.getKey();
                } else if (e.getValue() == maxF && e.getKey().compareTo(result) < 0) {
                    result = e.getKey();
                }

            return Arrays.stream(result.split("/")).filter(e -> e != "").toList();
        }
    }

    public static void main(String[] args) {
        System.out.println(new TopKUserVisited3PathsOnAWebsite().new Solution2().mostVisitedPattern(
            new String[] {"zkiikgv","zkiikgv","zkiikgv","zkiikgv"},
            new int[] {436363475,710406388,386655081,797150921},
            new String[] {"wnaaxbfhxp","mryxsjc","oz","wlarkzzqht"}
        ));
    }

}
