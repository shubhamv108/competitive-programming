package code.shubham.trees.bsttree.bst;

import java.util.ArrayList;
import java.util.List;

public class FindModeInBST {

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
        public int[] findMode(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            visit(root, -100001, 1, result);
            result.clear();
            return result.stream().mapToInt(Integer::intValue).toArray();
        }

        void visit(TreeNode n, int prev, int streak, List<Integer> result) {
            if (n == null)
                return;
            visit(n.left, prev, streak, result);
            if (n.val != prev)
                streak = 0;
            streak++;
            visit(n.right, n.val, streak, result);
        }
    }
}
