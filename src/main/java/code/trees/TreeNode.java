package code.trees;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode next;
    public TreeNode(int val) {
        this.val = val;
    }
}

class TreeNodeFunctions {
    public static TreeNode siblingConnectedNode (TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode r = q.poll();
            int n = q.size();
            q.add(r.left);
            q.add(r.right);
            for (int i = 0; i < n; i++) {
                TreeNode rr = q.poll();
                q.add(rr.left);
                q.add(rr.right);
                r.next = rr;
                r = rr;
            }
        }
        return null;
    }
}
