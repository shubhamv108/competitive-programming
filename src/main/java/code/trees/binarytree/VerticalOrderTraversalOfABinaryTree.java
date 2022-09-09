package code.trees.binarytree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class VerticalOrderTraversalOfABinaryTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // not correct for same row and col
    class Solution {
    TreeMap<Integer, LinkedList<Integer>> m = new TreeMap<>();
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        TreeMap<Integer, LinkedList<Integer>> m = new TreeMap<>();
        verticalTraversal(root, 0, m);
        result.addAll(m.values());
        return result;
    }

    public void verticalTraversal(TreeNode node, int col, TreeMap<Integer, LinkedList<Integer>> m) {
            if (node == null)
                return;

            LinkedList<Integer> l = m.get(col);
            if (l == null)
                m.put(col, l = new LinkedList<>());
            l.add(node.val);

            verticalTraversal(node.left, col-1, m);
            verticalTraversal(node.right, col+1, m);
        }
    }

    class Solution2 {
        public List<List<Integer>> verticalTraversal(TreeNode root) {

            TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> m = new TreeMap<>();
            verticalTraversal(root, 0, 0, m);

            List<List<Integer>> result = new ArrayList<>(m.size());
            for (Map.Entry<Integer, TreeMap<Integer, PriorityQueue<Integer>>> entry : m.entrySet()) {

                LinkedList<Integer> l = new LinkedList<>();
                for (Map.Entry<Integer, PriorityQueue<Integer>> e : entry.getValue().entrySet()) {
                    PriorityQueue<Integer> pq = e.getValue();
                    while (!pq.isEmpty())
                        l.add(pq.poll());
                }

                result.add(l);
            }
            return result;
        }

        public void verticalTraversal(TreeNode node, int col, int row, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> m) {
            if (node == null)
                return;

            TreeMap<Integer, PriorityQueue<Integer>> l = m.get(col);
            if (l == null)
                m.put(col, l = new TreeMap<>());
            PriorityQueue<Integer> r = l.get(row);
            if (r == null)
                l.put(row, r = new PriorityQueue<>());
            r.offer(node.val);

            verticalTraversal(node.left,  col-1, row+1, m);
            verticalTraversal(node.right, col+1, row+1, m);
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        System.out.println(
                new VerticalOrderTraversalOfABinaryTree().new Solution2().verticalTraversal(root)
        );
    }
}
