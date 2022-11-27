package code.shubham.trees;

import java.util.Arrays;
import java.util.Collection;

public class FormTreeFromArray {

    private Integer[] arr;

    public FormTreeFromArray(final Object arr) {
        this.arr = transform(arr);
    }

    private int leftPos(final int n) {
        return (2*n) + 1;
    }

    private int rightPos(final int n) {
        return (2*n) + 2;
    }

    public TreeNode get() {
        if (arr == null || arr.length == 0)
            return null;
        return deserialize(0);
    }

    public TreeNode deserialize(int pos) {
        if (pos >= arr.length || arr[pos] == null)
            return null;
        TreeNode root = new TreeNode(arr[pos]);
        root.left  = deserialize(leftPos(pos));
        root.right = deserialize(rightPos(pos));
        return root;
    }

    private Integer[] transform(Object o) {
        if (o instanceof int[])
            return Arrays.stream((int[]) o).boxed().toArray(Integer[]::new);
        if (o instanceof Collection)
             return ((Collection<Integer>) o)
                    .stream()
                     .toArray(Integer[]::new);
        if (o instanceof Integer[])
            return (Integer[]) o;
        return null;
    }

    public static TreeNode getTreeFromArray(int[] a) {
        return getTreeFromArray(Arrays.stream(a).boxed().toArray(Integer[]::new));
    }

    public static TreeNode getTreeFromArray(Integer[] a) {
        return new FormTreeFromArray(a).get();
    }

}
