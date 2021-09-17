package code.trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PostOrderIteratively {

    private class Pair {
        private TreeNode n;
        private int state;
        private Pair(final TreeNode n, final int state) {
            this.n = n;
            this.state = state;
        }
    }

    private class Solution {

        void print(TreeNode root) {
            Stack<Pair> s = new Stack<>();
            s.add(new Pair(root, 0));
            while (!s.isEmpty()) {
                Pair t = s.peek();
                if (t.state == 2) {
                    System.out.print(t.n.val + " ");
                    s.pop();
                    if (!s.isEmpty())
                        s.peek().state += 1;
                } else if (t.state == 0)
                    if (t.n.left != null)
                        s.push(new Pair(t.n.left, 0));
                    else
                        t.state += 1;
                else if (t.state == 1)
                    if (t.n.right != null)
                        s.push(new Pair(t.n.right, 0));
                    else
                        t.state += 1;
            }
        }
    }

    class Solution2 {
        public List<Integer> postorderTraversal(TreeNode root) {
            LinkedList<Integer> result = new LinkedList<>();
            if (root == null) return result;
            Deque<TreeNode> stack = new LinkedList<>();
            TreeNode p = root;
            while (!stack.isEmpty() || p != null) {
                if (p != null) {
                    stack.push(p);
                    result.offerFirst(p.val);
                    p = p.right;
                } else {
                    TreeNode node = stack.pop();
                    p = node.left;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(8);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(9);
        new PostOrderIteratively().new Solution().print(root);
    }


}
