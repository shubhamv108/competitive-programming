package code.shubham.trees.binarytree;

public class BinaryTreeUpsideDown {
      public class TreeNode {
          public int val;
          public TreeNode left, right;
          public TreeNode(int val) {
              this.val = val;
              this.left = this.right = null;
          }
      }

    public class Solution {
        /**
         * @param root: the root of binary tree
         * @return: new root
         */
        public TreeNode upsideDownBinaryTree(TreeNode root) {
            if (root == null)
                return root;
            return recurse(root);
        }

        TreeNode recurse(TreeNode node) {
            if (node.left == null)
                return node;

            TreeNode n = recurse(node.left);
            node.left.right = node;
            node.left.left = node.right;
            node.left = null;
            node.right = null;
            return n;
        }
    }
}
