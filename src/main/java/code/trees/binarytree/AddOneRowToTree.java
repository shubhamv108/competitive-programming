package code.trees.binarytree;

import java.util.LinkedList;

public class AddOneRowToTree {

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
        public TreeNode addOneRow(TreeNode root, int val, int depth) {
            if (depth == 1) {
                TreeNode newRoot = new TreeNode(val);
                newRoot.left = root;
                newRoot.right = null;
                return newRoot;
            }


            LinkedList<TreeNode> q = new LinkedList<>();
            q.offer(root);
            int curDepth = 0, size;
            TreeNode p, temp, left, right;

            while (!q.isEmpty()) {
                size = q.size();
                curDepth++;
                for (int i = 0; i < size; i++) {
                    p = q.poll();
                    left = p.left;
                    right = p.right;

                    if (curDepth == depth - 1) {
                        temp = p.left;
                        p.left = new TreeNode(val);
                        p.left.left = temp;
                        left = temp;
                        temp = p.right;
                        p.right = new TreeNode(val);
                        p.right.right = temp;
                        right = temp;
                    }

                    if (left != null)
                        q.offer(left);
                    if (right != null)
                        q.offer(right);
                }
            }
            return root;
        }
    }

    class Solution2 {
        public TreeNode addOneRow(TreeNode root, int val, int depth) {
            return f(root, 1, true, val, depth);
        }

        TreeNode f(TreeNode node, int curDepth, boolean isLeft, int val, int depth) {
            if (curDepth == depth) {
                TreeNode newNode = new TreeNode(val);
                if (isLeft)
                    newNode.left = node;
                else
                    newNode.right = node;
                return newNode;
            }

            if (node == null)
                return null;

            node.left  = f(node.left,  curDepth + 1, true,  val, depth);
            node.right = f(node.right, curDepth + 1, false, val, depth);

            return node;
        }
    }

    class Solution3 {
        public TreeNode addOneRow(TreeNode root, int v, int d) {
            if (d == 0 || d == 1) {
                TreeNode newRoot = new TreeNode(v);
                newRoot.left = d == 1 ? root : null;
                newRoot.right = d == 0 ? root : null;
                return newRoot;
            }
            if (root != null && d >= 2) {
                root.left  = addOneRow(root.left,  v, d > 2 ? d - 1 : 1);
                root.right = addOneRow(root.right, v, d > 2 ? d - 1 : 0);
            }
            return root;
        }
    }
}
