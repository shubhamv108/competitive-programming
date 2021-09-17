package code.trees.binarytree;

import java.util.Arrays;

public class DiameterOfBinaryTree {
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

        @Override
        public String toString() {
            return "" + val;
        }
    }
    class Solution {
        int diameter = 0;
        public int diameterOfBinaryTree(TreeNode root) {
            populateDiameter(root);
            return diameter;
        }

        public int populateDiameter(TreeNode node) {
            if (node == null) return 0;
            int lH = populateDiameter(node.left);
            int rH = populateDiameter(node.right);
            this.diameter = Math.max(this.diameter, lH + rH);
            return 1 + Math.max(lH, rH);
        }
    }

    class Solution2PrintEndNodes {
        class Pair {
            TreeNode node;
            int d;
            Pair(TreeNode node, int d) {
                this.node = node;
                this.d = d;
            }
        }

        int diameter = 0;
        TreeNode[] result = new TreeNode[2];
        public TreeNode[] diameterOfBinaryTree(TreeNode root) {
            populateDiameter(root);
            return result;
        }

        public Pair populateDiameter(TreeNode node) {
            if (node == null) return new Pair(null, 0);
            Pair lH = populateDiameter(node.left);
            if (lH.node == null) lH.node = node;
            Pair rH = populateDiameter(node.right);
            if (rH.node == null) rH.node = node;
            if (this.diameter < lH.d + rH.d) {
                this.diameter = lH.d + rH.d;
                this.result[0] = lH.node;
                this.result[1] = rH.node;
            }
            if (lH.d >= rH.d) {
                lH.d = 1 + lH.d;
                return lH;
            }  else {
                rH.d = 1 + rH.d;
                return rH;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node.left = node2;
        node.right = node3;
        node2.left = node4;
        node2.right = node5;
        Arrays.stream(
                new DiameterOfBinaryTree().new Solution2PrintEndNodes().diameterOfBinaryTree(node))
                .forEach(System.out::println);
    }
}
