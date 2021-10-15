package code.contestpractice.oa.microsoft;

public class CountVisibleNodesInBinaryTree {
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
        int count = 0;
        public int goodNodes(TreeNode root) {
            this.goodNodes(root, root.val);
            return count;
        }

        void goodNodes(TreeNode root, int max) {
            if (root == null) return;
            if (root.val >= max) count++;
            if (root.left != null) this.goodNodes(root.left, Math.max(max, root.val));
            if (root.right != null) this.goodNodes(root.right, Math.max(max, root.val));
        }
    }
}
