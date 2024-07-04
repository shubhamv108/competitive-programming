package code.shubham.trees.binarytree;

public class SmallestStringStartingFromLeaf {

      public class TreeNode {
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
        StringBuilder result;
        public String smallestFromLeaf(TreeNode root) {
            recurse(root, new StringBuilder());
            return result.toString();
        }

        void recurse(TreeNode node, StringBuilder s) {
            if (node == null)
                return;

            s.append((char) (node.val + 97));
            if (node.left == null && node.right == null) {
                s.reverse();
                if (result == null || s.compareTo(result) < 0) {
                    result = new StringBuilder();
                    result.append(s.toString());
                }
                s.reverse();
                return;
            } else {
                recurse(node.left, s);
                recurse(node.right, s);
            }
            s.deleteCharAt(s.length() - 1);
        }
    }

    public static void main(String[] args) {
        SmallestStringStartingFromLeaf smallestStringStartingFromLeaf = new SmallestStringStartingFromLeaf();
        Solution solution = smallestStringStartingFromLeaf.new Solution();
       //  [25,1,3,1,3,0,2]
        TreeNode root = smallestStringStartingFromLeaf.new TreeNode(25);
        root.left = smallestStringStartingFromLeaf.new TreeNode(1);
        root.right = smallestStringStartingFromLeaf.new TreeNode(3);
        root.left.left = smallestStringStartingFromLeaf.new TreeNode(1);
        root.left.right = smallestStringStartingFromLeaf.new TreeNode(3);
        root.right.left = smallestStringStartingFromLeaf.new TreeNode(0);
        root.right.right = smallestStringStartingFromLeaf.new TreeNode(2);
        System.out.println(solution.smallestFromLeaf(root));
    }
}
