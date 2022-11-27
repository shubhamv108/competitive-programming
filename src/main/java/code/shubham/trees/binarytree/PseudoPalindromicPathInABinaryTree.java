package code.shubham.trees.binarytree;

public class PseudoPalindromicPathInABinaryTree {

      public static class TreeNode {
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
        public int pseudoPalindromicPaths (TreeNode root) {
            return pseudoPalindromicPaths(root, 0);
        }

        public int pseudoPalindromicPaths (TreeNode root, int count) {
            if (root == null)
                return 0;
            count ^= 1 << (root.val - 1);
            int paths = pseudoPalindromicPaths(root.left, count) + pseudoPalindromicPaths(root.right, count);
            if (root.left == root.right && (count & count - 1) == 0)
                paths++;
            return paths;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(1);
        root.right.right = new TreeNode(1);
        System.out.println(
                new PseudoPalindromicPathInABinaryTree().new Solution().pseudoPalindromicPaths(root)
        );
    }
}
