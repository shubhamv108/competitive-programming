package code.trees.segmenttrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class PersistentSegmentTree {

    List<Node> versions;
    int capacity;

    PersistentSegmentTree(int[] array) {
        if (Objects.isNull(array)) throw new IllegalArgumentException();
        this.capacity = array.length;
        versions = new ArrayList<>();
        versions.add(construct(array, 0, array.length - 1));
    }

    static class Node {
        int value;
        Node left;
        Node right;
        Node(int value) { this.value = value; }
        Node(Node left, Node right) {
            this.left = left;
            this.right = right;
            this.value = left.value + right.value;
        }

        @Override
        public String toString() {
            return "value=" + value;
        }
    }

    Node construct(int[] array, int start, int end) {
        if (start == end) return new Node(array[start]);
        int mid = start + (end - start) / 2;
        return new Node(construct(array, start, mid),
                        construct(array, mid + 1, end));
    }

    void update(int value, int position) {
        versions.add(update(versions.get(versions.size() - 1), value, position, 0, capacity - 1));
    }

    Node update(Node node, int value, int position, int start, int end) {
        if (position < start || position > end) return node;
        if (start == end) return new Node(value);
        int mid = start + (end - start) / 2;
        return new Node(update(node.left, value, position, start, mid),
                        update(node.right, value, position, mid + 1, end));
    }

    int getSumForRange(int rangeStart, int rangeEnd) {
       return getSumForRange(versions.size(), rangeStart, rangeEnd);
    }

    int getSumForRange(int version, int rangeStart, int rangeEnd) {
        if (version > versions.size()) throw  new IllegalArgumentException();
        return getSumForRange(versions.get(version - 1), rangeStart, rangeEnd, 0, capacity - 1);
    }

    int getSumForRange(Node node, int rangeStart, int rangeEnd, int start, int end) {
        if (rangeStart <= start && end <= rangeEnd) {
            return node.value;
        }
        if (rangeStart > end || start > rangeEnd) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        return getSumForRange(node.left, rangeStart, rangeEnd, start, mid)
             + getSumForRange(node.right, rangeStart, rangeEnd, mid + 1, end);
    }

    void print(int version) {
        if (version > versions.size()) throw new IllegalArgumentException();
        print(versions.get(version - 1));
    }

    void print(Node node) {
        System.out.println();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node temp = queue.poll();
                System.out.print(temp.value + " ");
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] { 1, 3, 5, -7, 9, 11 };
        PersistentSegmentTree segmentTree = new PersistentSegmentTree(array);
        segmentTree.print(1);
        System.out.println(segmentTree.getSumForRange(1, 5));
        segmentTree.update(-10, 2);
        segmentTree.print(1);
        System.out.println(segmentTree.getSumForRange(1, 1, 5));
        segmentTree.print(2);
        System.out.println(segmentTree.getSumForRange(1, 5));
    }

}
