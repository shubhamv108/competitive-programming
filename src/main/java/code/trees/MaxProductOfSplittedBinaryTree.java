package code.trees;

public class MaxProductOfSplittedBinaryTree {

      private class TreeNode {
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

    class Solution {
        int MOD = (int) 1e9 + 7;
        long result = Integer.MIN_VALUE, totalSum;
        public int maxProduct(TreeNode root) {
            if (root == null) return 0;
            totalSum = sum(root);
            maxProd(root);
            return (int) (result % MOD);
        }

        int maxProd(TreeNode node) {
            if (node == null) return 0;
            int sumLeft  = maxProd(node.left);
            int sumRight = maxProd(node.right);
            result = Math.max(result, Math.max(((totalSum - sumLeft) * sumLeft),
                                              (sumRight * (totalSum - sumRight))));
            return node.val + sumLeft + sumRight;
        }

        int sum(TreeNode node) {
            return node == null ? 0 : node.val + sum(node.left) + sum(node.right);
        }
    }

}
