package code.trees.binarytree;

public class SubtreeOfAnoherTree {
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
     
    class Solution {
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            if (isSame(root, subRoot)) return true;
            if (root == null && subRoot != null) return false;
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }

        boolean isSame(TreeNode node, TreeNode subTreeNode) {
            if (node == null && subTreeNode == null) return true;
            if (node != null && subTreeNode == null) return false;
            if (node == null && subTreeNode != null) return false;
            if (node.val != subTreeNode.val) return false;
            return this.isSame(node.left, subTreeNode.left)
                    && this.isSame(node.right, subTreeNode.right);
        }
    }

}
