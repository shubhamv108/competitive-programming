package code.shubham.trees.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeZigzagLevelOrderTraversal {

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
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) return result;

            Deque<TreeNode> q = new LinkedList<>();
            q.offer(root);
            boolean flag = true;
            while(!q.isEmpty()) {
                int size = q.size();
                List<Integer> l = new ArrayList<>();
                while (size-- > 0) {
                    TreeNode t = flag ? q.poll() : q.pollLast();
                    l.add(t.val);
                    if (flag) {
                        if (t.left != null) q.offer(t.left);
                        if (t.right != null) q.offer(t.right);
                    } else {
                        if (t.right != null) q.offerFirst(t.right);
                        if (t.left != null) q.offerFirst(t.left);
                    }
                }
                if (l.size() > 0) result.add(l);
                flag = !flag;
            }
            return result;
        }
    }

    class Solution2 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) return result;

            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();
            stack1.push(root);
            while (!stack1.isEmpty() || !stack2.isEmpty()) {
                ArrayList<Integer> l = new ArrayList<>();
                while (!stack1.isEmpty()) {
                    TreeNode t = stack1.pop();
                    l.add(t.val);
                    if (t.left != null) stack2.push(t.left);
                    if (t.right != null) stack2.push(t.right);
                }
                if (!l.isEmpty()) result.add(l);
                l  = new ArrayList<>();
                while (!stack2.isEmpty()) {
                    TreeNode t = stack2.pop();
                    l.add(t.val);
                    if (t.right != null) stack1.push(t.right);
                    if (t.left != null) stack1.push(t.left);
                }
                if (!l.isEmpty()) result.add(l);
            }
            return result;
        }
    }

    class Solution3 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) return result;

            LinkedList<TreeNode> q = new LinkedList<>();
            q.offer(root);
            int level = 1;
            while (!q.isEmpty()) {
                int size = q.size();
                ArrayList<Integer> l = new ArrayList<>();;
                while (size-- > 0) {
                    TreeNode t = q.poll();
                    l.add(t.val);
                    if (t.left != null) q.add(t.left);
                    if (t.right != null) q.add(t.right);
                }
                if ((level & 1) == 0) Collections.reverse(l);
                level++;
                result.add(l);
            }
            return result;
        }
    }
    
}
