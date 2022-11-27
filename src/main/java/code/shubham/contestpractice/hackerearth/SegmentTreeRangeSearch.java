package code.shubham.contestpractice.hackerearth;

import input.InputUtils;

import java.util.stream.IntStream;

public class SegmentTreeRangeSearch {

    static class SegmentTree {
        Node root;
        SegmentTree(int[] A) {
            root = construct(A, 1, 0, A.length - 1);
        }

        Node construct(int[] A, int mStart, int start, int end) {
            if (start == end) return new Node(mStart, A[start], start);
            int mid = start + (end - start) / 2;
            Node left = construct(A, mStart, start, mid);
            Node right = construct(A, left.e + 1, mid + 1, end);
            return new Node(mStart, right.e, left, right);
        }

        int query(int m) {
            return query(root, m);
        }

        int query(Node node, int m) {
            if (m < node.s || m > node.e) return -1;
            if (node.pos != null) return node.pos;
            int result = query(node.left, m);
            if (result == -1) result = query(node.right, m);
            return result;
        }
    }

    static class Node {
        int s;
        int e;
        Integer pos;
        Node left;
        Node right;
        Node(int s, int e, int pos){
            this.s = s;
            this.e = e;
            this.pos = pos;
        }
        Node(int s, int e, Node left, Node right) {
            this.s = s;
            this.e = e;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        int[] A = InputUtils.nextIntLine();
        IntStream.range(1, A.length).forEach(i -> A[i] += A[i-1]);
        SegmentTree tree = new SegmentTree(A);
        System.out.println(tree.query(InputUtils.nextInt()));
    }

}
