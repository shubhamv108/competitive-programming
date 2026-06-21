package code.shubham.graphs.trees;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class MinimumHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] A) {
        HashMap<Integer, HashSet<Integer>> tree = new HashMap<>();

        IntStream.range(0, A.length).forEach(r -> {
            HashSet<Integer> children = tree.get(A[r][0]);
            if (children == null) tree.put(A[r][0], children = new HashSet<Integer>());
            children.add(A[r][1]);

            children = tree.get(A[r][1]);
            if (children == null) tree.put(A[r][1], children = new HashSet<Integer>());
            children.add(A[r][0]);
        });

        List<Integer> leaves = null;

        while (tree.size() > 0) {
            leaves = new ArrayList<>();
            for (Integer k : tree.keySet())
                if (tree.get(k).size() < 2)
                    leaves.add(k);

            for (Integer k : leaves) {
                Integer connectedNode = tree.get(k).stream().findFirst().orElse(null);
                if (connectedNode != null) {
                    HashSet<Integer> connectedNodeChildren = tree.get(connectedNode);
                    connectedNodeChildren.remove(k);
                }
                tree.remove(k);
            }
        }

        return leaves;
    }

    class Solution2 {
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

    public static void main(String[] args) {
        int[][] a = {{1,0}, {1,2}, {1,3}};
        System.out.println(
                new MinimumHeightTrees().findMinHeightTrees(4, a)
        );
    }

}
