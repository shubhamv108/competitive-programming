package code.shubham.strings;

import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "";

        StringBuilder result = new StringBuilder();
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode p = q.poll();
            result.append(p == null ? "-" : p.val).append(",");
            if (p == null)
                continue;
            q.offer(p.left);
            q.offer(p.right);
        }

        if (!result.isEmpty())
            result.setLength(result.length() - 1);

        return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data))
            return null;

        String[] A = data.split(",");
        TreeNode result = new TreeNode(Integer.valueOf(A[0]));

        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(result);

        for (int i = 1; i < A.length; ++i) {
            TreeNode p = q.poll();
            if (!"-".equals(A[i])) {
                p.left = new TreeNode(Integer.valueOf(A[i]));
                q.offer(p.left);
            }
            ++i;
            if (!"-".equals(A[i])) {
                p.right = new TreeNode(Integer.valueOf(A[i]));
                q.offer(p.right);
            }
        }

        return result;
    }

    public static void main(String[] args) {
         TreeNode root = new TreeNode(1);
         root.left = new TreeNode(2);
         root.right = new TreeNode(3);
         root.right.left = new TreeNode(4);
         root.right.right = new TreeNode(5);
         root.right.left.left = new TreeNode(6);
         root.right.left.right = new TreeNode(7);
         Codec ser = new Codec();
         Codec deser = new Codec();
         System.out.println(ser.serialize(root));
         TreeNode ans = deser.deserialize(ser.serialize(root));
    }
}