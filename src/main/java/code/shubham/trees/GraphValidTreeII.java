package code.shubham.trees;

import java.util.HashMap;
import java.util.Map;

public class GraphValidTreeII {
    public class Solution {
        /**
         * @param a: the node a
         * @param b: the node b
         * @return: nothing
         */
        UF uf = new UF();
        public void addEdge(int a, int b) {
            uf.add(a);
            uf.add(b);
            uf.union(a, b);
        }

        /**
         * @return: check whether these edges make up a valid tree
         */
        public boolean isValidTree() {
            return uf.edges + 1 == uf.parent.size() && uf.count == 1;
        }

        class UF {
            Map<Integer, Integer> parent = new HashMap<>();
            int count, edges;

            void add(int a) {
                if (parent.get(a) == null) {
                    parent.put(a, a);
                    count++;
                }
            }

            int find(int a) {
                if (parent.get(a) != a)
                    parent.put(a, find(parent.get(a)));
                return parent.get(a);
            }

            void union(int x, int y) {
                x = find(x);
                y = find(y);
                if (x != y) {
                    parent.put(y, x);
                    --count;
                }
                edges++;
            }
        }
    }
}
