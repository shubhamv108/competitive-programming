package code.trees.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWidthOfBinaryTree {
      private class TreeNode {
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

        class Pair {
            TreeNode node;
            int number;
            Pair(TreeNode node, int number) {
                this.node = node;
                this.number = number;
            }
        }

        public int widthOfBinaryTree(TreeNode root) {
            if (root == null) return 0;
            int result = 0;
            if (root == null) return result;
            Queue<Pair> q = new LinkedList<>();
            q.offer(new Pair(root, 0));
            while (!q.isEmpty()) {
                int size = q.size();
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
                while (size-- > 0) {
                    Pair p = q.poll();
                    min = Math.min(p.number, min);
                    max = Math.max(p.number, max);
                    if (p.node.left != null)
                        q.offer(new Pair(p.node.left, (2 * p.number) + 1));
                    if (p.node.right != null)
                        q.offer(new Pair(p.node.right, (2 * p.number) + 2));
                }
                result = Math.max(result, max - min + 1);
            }
            return result == 0 ? 1 : result;
        }
    }
}
