package code.shubham.trees;

import java.util.Deque;
import java.util.LinkedList;


public class CompleteBinaryTreeInserter {

    TreeNode root;
    Deque<TreeNode> q = new LinkedList<>();

    public CompleteBinaryTreeInserter(TreeNode root) {
        this.root = root;
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.peek();
            if (node.left != null)
                q.offer(node.left);
            if (node.right != null)
                q.offer(node.right);
            if (node.left != null && node.right != null)
                q.poll();
            else
                break;
        }
    }

    public int insert(int v) {
        TreeNode node = q.peekFirst();
        q.offerLast(new TreeNode(v));
        if (node.left == null) {
            node.left = q.peekLast();
        } else {
            node.right = q.peekLast();
            q.pollFirst();
        }
        return node.val;
    }

    public TreeNode get_root() {
        return this.root;
    }

    class TreeNode {
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
}