package code.shubham.trees;

public class IsBalancedBinaryTree {
    class Solution {
        public boolean isBalanced(TreeNode root) {
            if (root == null) return true;
            int diff = Math.abs(height(root.left) - height(root.right));
            return (diff == 0 || diff == 1) && isBalanced(root.left) && isBalanced(root.right);
        }

        int height(TreeNode node) {
            return node == null ? 0 : 1 + Math.max(height(node.left), height(node.right));
        }
    }
}
