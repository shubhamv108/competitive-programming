package code.shubham.graphs.trees.binaryindextree;

public class BinaryIndexTree {

    private final int[] tree;
    private final int size;

    public BinaryIndexTree(int size) {
        this.size = size;
        this.tree = new int[this.size + 1];
    }

    public int sum(int index) {
        int result = 0;
        while (index > 0) {
            result += tree[index];
            index = parent(index);
        }
        return result;
    }

    public void insert(int index, int value) {
        ++index;
        while (index < size) {
            tree[index] += value;
            index = next(index);
        }
    }

    public int parent(int x) {
        return x & (x - 1);
    }

    public int next(int x) {
        return x + (x & -x);
    }
}