package code.trees;

import java.util.LinkedList;
import java.util.Queue;

public class ConstructTreeFromLevelOrder {

    public static int left  (int i) { return (2*i) + 1; }
    public static int right (int i) { return (2*i) + 2; }

    private Queue<ConstructTreeNode> q = null;

    int[] a = null;

    public ConstructTreeFromLevelOrder (int[] a) {
        this.a = a;
    }

    private TreeNode getTreeFromLevelOrderRecursive (TreeNode node, int i) {
        if (a[i] == -1) return null;
        node = new TreeNode(a[i]);
        if (left(i)  < a.length) getTreeFromLevelOrderRecursive (node.left, left (i));
        if (right(i) < a.length) getTreeFromLevelOrderRecursive (node.right, right(i));
        return node;
    }

    public TreeNode getTreeFromLevelOrderRecursive (int[] a) {
        if (a.length == 0) return null;
        return getTreeFromLevelOrderRecursive (null, 0);
    }

    private class ConstructTreeNode {
        public TreeNode n;
        public int pos;
        public ConstructTreeNode (TreeNode n, int pos) {
            this.n   = n;
            this.pos = pos;
        }
    }

    public TreeNode getTreeFromLevelOrder () {
        if (a.length == 0 || a[0] == -1) return null;
        Queue<ConstructTreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(a[0]);
        q.offer(new ConstructTreeNode(root, 0));
        ConstructTreeNode n;
        while (!q.isEmpty()) {
            n = q.poll();
            if (isValidTreeNode(left(n.pos))) {
                n.n.left  = new TreeNode(left(n.pos));
                q.offer(new ConstructTreeNode(n.n.left, left(n.pos)));
            }
            if (isValidTreeNode(right(n.pos))) {
                n.n.right  = new TreeNode(right(n.pos));
                q.offer(new ConstructTreeNode(n.n.right, right(n.pos)));
            }
        }
        return root;
    }

    private boolean isValidTreeNode (int i) {
        return i < a.length && a[i] != -1;
    }

}
