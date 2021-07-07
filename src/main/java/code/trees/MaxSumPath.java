package code.trees;

public class MaxSumPath {

    int max = Integer.MIN_VALUE;

    int max(Node root) {
        if (null == root) return 0;
        int lSum = max(root.left);
        int rSum = max(root.right);
        max = Math.max(max, root.val + lSum + rSum);
        return root.val + Math.max(lSum, rSum);
    }

    class Node {
        int val;
        Node left;
        Node right;
    }

}
