package code.trees.bsttree;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTreesII {


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
        public List<TreeNode> generateTrees(int n) {
            return generateTrees(1, n);
        }

        private List<TreeNode> generateTrees(int start, int end) {
            List<TreeNode> nodes = new ArrayList<>();
            if (start > end) {
                nodes.add(null);
                return nodes;
            }

            if (start == end) {
                nodes.add(new TreeNode(start));
                return nodes;
            }

            for (int i = start; i <= end; i++) {
                List<TreeNode> leftNodes = this.generateTrees(start, i-1);
                List<TreeNode> rightNodes = this.generateTrees(i+1, end);
                for (TreeNode leftNode : leftNodes) {
                    for (TreeNode rightNode : rightNodes) {
                        TreeNode root = new TreeNode(i);
                        root.left = leftNode;
                        root.right = rightNode;
                        nodes.add(root);
                    }
                }
            }
            return nodes;
        }
    }

}
