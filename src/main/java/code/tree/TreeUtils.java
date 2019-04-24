package code.tree;

public class TreeUtils {

    public static TreeNode getTreeFromLevelOrder (int[] a) {
        return new ConstructTreeFromLevelOrder(a).getTreeFromLevelOrder();
    }

}
