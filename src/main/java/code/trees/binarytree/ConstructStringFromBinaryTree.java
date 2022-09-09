package code.trees.binarytree;

public class ConstructStringFromBinaryTree {

      public static class TreeNode {
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
        public String tree2str(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            f(root, sb);
            return sb.toString();
        }

        void f(TreeNode node, StringBuilder sb) {
            if (node == null)
                return;

            sb.append(node.val);

            if (node.left != null) {
                if (node.val == 2) {
                    System.out.println("2.left=>"+ "  " +node.left);
                }
                sb.append('(');
                f(node.left, sb);
                sb.append(')');
            }

            if (node.right != null) {
                sb.append('(');
                f(node.right, sb);
                sb.append(')');
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);

        System.out.println(
                new ConstructStringFromBinaryTree().new Solution().tree2str(root)
        );
    }
}
