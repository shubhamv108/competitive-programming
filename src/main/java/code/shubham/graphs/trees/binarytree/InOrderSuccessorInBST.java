package code.shubham.graphs.trees.binarytree;

import code.shubham.graphs.trees.TreeNode;

public class InOrderSuccessorInBST {
    public class Solution {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            TreeNode cand = null;

            while (root != null) {
                if (p.val >= root.val) {
                    root = root.right;
                } else {
                    cand = root;
                    root = root.left;
                }
            }

            return cand;
        }
    }
}
