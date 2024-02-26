package code.shubham.trees.binarytree;

public class LongestUnivaluePath {
      public class TreeNode {
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
        int result = 0;
        public int longestUnivaluePath(TreeNode root) {
            recurse(root);
            return result;
        }

        int recurse(TreeNode root) {
            if (root == null)
                return 0;

            if (root.left == null && root.right == null)
                return 0;

            int lc = recurse(root.left);
            int rc = recurse(root.right);

            int l = 0, r = 0;

            if (root.left != null && root.val == root.left.val)
                l += lc + 1;
            if (root.right != null && root.val == root.right.val)
                r += rc + 1;

            result = Math.max(result, l + r);

            return Math.max(l, r);
        }
    }

    public static void main(String[] args) {
        final LongestUnivaluePath longestUnivaluePath = new LongestUnivaluePath();
        final Solution solution = longestUnivaluePath.new Solution();
        final TreeNode root = longestUnivaluePath.new TreeNode(5);
        root.left = longestUnivaluePath.new TreeNode(4);
        root.left.left = longestUnivaluePath.new TreeNode(1);
        root.left.right = longestUnivaluePath.new TreeNode(1);
        root.right = longestUnivaluePath.new TreeNode(5);
        root.right.right = longestUnivaluePath.new TreeNode(5);

        System.out.println(solution.longestUnivaluePath(root));
    }
}
