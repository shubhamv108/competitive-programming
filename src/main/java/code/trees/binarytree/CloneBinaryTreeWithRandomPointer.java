package code.trees.binarytree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class CloneBinaryTreeWithRandomPointer {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode random;
        TreeNode(int val) { this.val = val; }

        @Override
        public String toString() {
            String memLoc = super.toString();
            memLoc = memLoc.substring(memLoc.indexOf('@') + 1);
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + Optional.ofNullable(left).map(e -> e.val).orElse(null) +
                    ", right=" + Optional.ofNullable(right).map(e -> e.val).orElse(null) +
                    ", random=" + Optional.ofNullable(random).map(e -> e.val).orElse(null) +
                    '}' + memLoc;
        }

        public void print() {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(this);
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    TreeNode node = q.poll();
                    System.out.print(node + "--");
                    if (node.left != null) q.offer(node.left);
                    if (node.right != null) q.offer(node.right);
                }
                System.out.println();
            }
        }
    }

    class Solution{
        public TreeNode clone(TreeNode tree){
            if (tree == null) return null;
            TreeNode copyRoot = copyLeftRightPointerNodes(tree);
            copyRandomPointer(tree);
            restoreOriginalTreeNode(tree);
            return copyRoot;
        }

        TreeNode copyLeftRightPointerNodes(TreeNode tree) {
            if (tree == null) return null;
            TreeNode copy = new TreeNode(tree.val);
            TreeNode t = tree.left;
            tree.left = copy;
            copy.left = t;
            if (t != null)
                copyLeftRightPointerNodes(t);
            copy.right = copyLeftRightPointerNodes(tree.right);
            return copy;
        }


        void copyRandomPointer(TreeNode tree) {
            if (tree == null) return;
            TreeNode copy = tree.left;
            if (tree.random != null)
                copy.random = tree.random.left;
            copyRandomPointer(copy.left);
            copyRandomPointer(tree.right);
        }

        void restoreOriginalTreeNode(TreeNode tree) {
            if (tree == null) return;
            TreeNode copy = tree.left;
            tree.left = copy.left;
            if (tree.left != null) {
                copy.left = copy.left.left;
            }
            restoreOriginalTreeNode(tree.left);
            restoreOriginalTreeNode(tree.right);
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(5);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.random = node3;
        node5.random = node1;
        node1.print();

        System.out.println("\n");

        new CloneBinaryTreeWithRandomPointer().new Solution().clone(node1).print();

        System.out.println("\n");
        node1.print();
    }
}