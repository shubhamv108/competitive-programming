package code.trees;

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

    public static void main(String[] args) {
        int[][] a = {{1,0}, {1,2}, {1,3}};
        System.out.println(
                new MinimumHeightTrees().findMinHeightTrees(4, a)
        );
    }

}
