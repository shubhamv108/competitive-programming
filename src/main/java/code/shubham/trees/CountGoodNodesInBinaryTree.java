package code.shubham.trees;

public class CountGoodNodesInBinaryTree {
    public int goodNodes(TreeNode root) {
        return this.goodNodes(root, Integer.MIN_VALUE);
    }

    public int goodNodes(TreeNode root, int greatest) {
        if (root == null) return 0;
        greatest = Math.max(greatest, root.val);
        return
                this.goodNodes(root.left, greatest)
                +
                this.goodNodes(root.right, greatest)
                +
                (root.val == greatest ? 1 : 0);
    }

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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);
        System.out.println(
                new CountGoodNodesInBinaryTree().goodNodes(root)
        );
    }
}
