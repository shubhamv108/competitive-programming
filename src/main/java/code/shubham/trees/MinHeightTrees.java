package code.shubham.trees;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MinHeightTrees {
    class Solution {
        public List<Integer> findMinHeightTrees(int n, int[][] A) {
            HashMap<Integer, LinkedList<Integer>> g = new HashMap<>();
            int[] d = new int[n];
            for (int[] a : A) {
                g.computeIfAbsent(a[0], k -> new LinkedList()).add(a[1]);
                g.computeIfAbsent(a[1], k -> new LinkedList()).add(a[0]);
                ++d[a[0]];
                ++d[a[1]];
            }

            LinkedList<Integer> q = g
                    .entrySet()
                    .stream()
                    .filter(e -> e.getValue().size() == 1)
                    .map(e -> e.getKey())
                    .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);

            ArrayList<Integer> result = new ArrayList<>();
            while (!q.isEmpty()) {
                result.clear();
                int size = q.size();
                while (size-- > 0) {
                    int p = q.poll();
                    result.add(p);
                    for (Integer neighbour : g.get(p)) {
                        --d[neighbour];
                        if (d[neighbour] == 1)
                            q.offer(neighbour);
                    }
                }
            }
            return result;
        }
    }

    class Solution1 {
        public List<Integer> findMinHeightTrees(int n, int[][] A) {
            return new AbstractList<Integer>() {
                ArrayList<Integer> result;

                @Override
                public int size() {
                    init(n);
                    return result.size();
                }

                @Override
                public Integer get(int index) {
                    init(n);
                    return result.get(index);
                }

                private void init(int n) {
                    if (result != null)
                        return;

                    result = new ArrayList<>();

                    if (n == 1) {
                        result.add(0);
                        return;
                    }

                    int[][] d = new int[n][2];
                    for (int[] a : A) {
                        d[a[0]][0] ^= a[1];
                        d[a[1]][0] ^= a[0];
                        ++d[a[0]][1];
                        ++d[a[1]][1];
                    }

                    for (int i = 0; i < n; ++i)
                        if (d[i][1] == 1)
                            result.add(i);

                    while (n > 2) {
                        n -= result.size();
                        ArrayList<Integer> r = new ArrayList<>();
                        for (int curr : result) {
                            int neighbour = d[curr][0];
                            d[neighbour][0] ^= curr;
                            if (--d[neighbour][1] == 1)
                                r.add(neighbour);
                        }
                        result = r;
                    }
                }
            };
        }
    }
}
