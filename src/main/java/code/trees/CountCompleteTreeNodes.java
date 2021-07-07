package code.tree;

import java.util.LinkedList;
import java.util.Queue;

public class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int height = getHeightOfCompleteBinaryTree(root);
        int rightHeight = getRightHeightOfCompleteBinaryTree(root);
        if (height == rightHeight) {
            return (2 << (height-1)) - 1;
        }
        int level = 0;
        int nodesAtLastLevel = 0;
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        boolean flag = false;
        while (!q.isEmpty() && !flag) {
            int levelNodesCount = q.size();
            level++;
            for (int i = 0; i < levelNodesCount; i++) {
                TreeNode p = q.poll();
                if (level == height - 1) {
                    if (p.right == null) {
                        nodesAtLastLevel = 2 * i;
                        if (p.left != null) {
                            nodesAtLastLevel += 1;
                        }
                        flag = true;
                        break;
                    }
                } else {
                    q.offer(p.left);
                    q.offer(p.right);
                }
            }
        }
        return ((2 << (height-1 - 1)) - 1) + nodesAtLastLevel;
    }

    int getHeightOfCompleteBinaryTree(TreeNode node) {
        if (node == null) return 0;
        return 1 + getHeightOfCompleteBinaryTree(node.left);
    }

    int getRightHeightOfCompleteBinaryTree(TreeNode node) {
        if (node == null) return 0;
        return 1 + getRightHeightOfCompleteBinaryTree(node.right);
    }

    static class TreeNode {
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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        System.out.println(
                new CountCompleteTreeNodes().countNodes(root)
        );
    }

}