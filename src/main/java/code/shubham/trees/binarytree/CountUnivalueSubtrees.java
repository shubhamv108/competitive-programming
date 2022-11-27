package code.shubham.trees.binarytree;

public class CountUnivalueSubtrees {
      private static class TreeNode {
          public int val;
          public TreeNode left, right;
          public TreeNode(int val) {
              this.val = val;
              this.left = this.right = null;
          }
      }

    public class Solution {
        int result = 0;
        public int countUnivalSubtrees(TreeNode root) {
            count(root);
            return result;
        }
        public boolean count(TreeNode root) {
            if (root == null) return true;
            boolean left = count(root.left);
            boolean right = count(root.right);
            if (left && right) {
                if ((root.left == null || (root.left.val == root.val))
                     &&
                    (root.right == null || (root.right.val == root.val))) {
                    result++;
                    return true;
                }
            }
            return false;
        }
    }


}
