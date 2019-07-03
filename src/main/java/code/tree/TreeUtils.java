package code.tree;

import java.util.ArrayList;
import java.util.Comparator;

public class TreeUtils {

    public static TreeNode getTreeFromLevelOrder (int[] a) {
        return new ConstructTreeFromLevelOrder(a).getTreeFromLevelOrder();
    }

}
