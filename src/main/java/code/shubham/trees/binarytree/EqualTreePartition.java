package code.shubham.trees.binarytree;

import java.util.HashMap;
import java.util.HashSet;

public class EqualTreePartition {
    public class TreeNode {
          public int val;
          public TreeNode left, right;
          public TreeNode(int val) {
              this.val = val;
              this.left = this.right = null;
          }
      }

    public class Solution {
        int zeroFreq = 0;
        public boolean checkEqualTree(TreeNode root) {
            HashSet<Integer> sumFreq = new HashSet<>();
            int totalSum = recurse(root, sumFreq);
            if (totalSum == 0)
                return zeroFreq > 1;
            return (totalSum & 1) == 0 && sumFreq.contains(totalSum / 2);
        }

        int recurse(TreeNode n, HashSet<Integer> sums) {
            if (n == null)
                return 0;

            int sum = n.val + recurse(n.left, sums) + recurse(n.right, sums);
            if (sum == 0)
                ++this.zeroFreq;
            sums.add(sum);
            return sum;
        }
    }

    public static void main(String[] args) {
        final EqualTreePartition equalTreePartition = new EqualTreePartition();
        TreeNode root = equalTreePartition.new TreeNode(5);
        root.left = equalTreePartition.new TreeNode(-10);
        root.right = equalTreePartition.new TreeNode(10);
        root.right.left = equalTreePartition.new TreeNode(-2);
        root.right.right = equalTreePartition.new TreeNode(-3);
        System.out.println(equalTreePartition.new Solution().checkEqualTree(root));
    }
}
