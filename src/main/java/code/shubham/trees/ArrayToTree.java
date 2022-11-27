package code.shubham.trees;

import java.util.ArrayList;
import java.util.Comparator;

public class ArrayToTree {

    public static TreeNode convert(ArrayList<Integer> A) {
        A.sort(Comparator.comparingInt(x -> x));
        return convertSorted(A);
    }

    public static TreeNode convertSorted(ArrayList<Integer> a) {
        return convertSorted(a, 0, a.size() - 1);
    }

    public static TreeNode convertSorted(ArrayList<Integer> A, int l, int r) {
        if (l > r) return null;
        int mid       = (l + r) / 2;
        TreeNode node = new TreeNode(A.get(mid));
        node.left     = convertSorted(A, l, mid - 1);
        node.right    = convertSorted(A, mid + 1, r);
        return node;
    }

}