package code.shubham.trees;

import java.util.Stack;

public class BSTIterator {
    private TreeNode root;
    private Stack<TreeNode> s = new Stack<>();
    public BSTIterator(TreeNode root) {
        this.root = root;
        updateStack();
    }

    private void updateStack () {
        while (root != null) {
            s.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (!s.isEmpty()) return true;
        return false;
    }

    /** @return the next smallest number */
    public int next() {
        if (!hasNext()) throw new RuntimeException("End of Tree");
        TreeNode A = s.pop();
        root = A.right;
        updateStack();
        return A.val;
    }
}
