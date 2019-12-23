package code.tree;

public class TreeUtils {

    public static TreeNode getTreeFromLevelOrder (int[] a) {
        return new ConstructTreeFromLevelOrder(a).getTreeFromLevelOrder();
    }

    public static TreeNode getTreeFromArray(Object a) {
        return new FormTreeFromArray(a).get();
    }

    public static TreeNode getNodeWithValue(TreeNode root, int k) {
        if(root == null)  return null;
        if(root.val == k) return root;
        TreeNode a = getNodeWithValue(root.left, k);
        if(a != null) return a;
        else return getNodeWithValue(root.right, k);
    }

}
