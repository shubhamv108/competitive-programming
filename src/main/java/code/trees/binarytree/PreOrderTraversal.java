package code.trees.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreOrderTraversal {

      private static class TreeNode {
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
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (true) {
                while (!stack.isEmpty()) {
                    TreeNode node = stack.peek();
                    result.add(node.val);
                    if (node.left != null)
                        stack.push(node.left);
                    else break;
                }
                if (stack.isEmpty()) break;
                while (!stack.isEmpty()) {
                    TreeNode node = stack.pop();
                    if (node.right != null) {
                        stack.push(node.right);
                        break;
                    }
                }
            }
            return result;
        }
    }

    class Solution2 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;

            Stack<TreeNode> stack = new Stack<>();
            while (true) {
                while (root != null) {
                    result.add(root.val);
                    stack.push(root);
                    root = root.left;
                }
                if (stack.isEmpty()) break;
                root = stack.pop().right;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node.right = node2;
        System.out.println(
                new PreOrderTraversal().new Solution2().preorderTraversal(node)
        );
    }
    
}
