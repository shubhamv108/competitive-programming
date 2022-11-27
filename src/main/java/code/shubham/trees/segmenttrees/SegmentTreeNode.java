package code.shubham.trees.segmenttrees;

public class SegmentTreeNode {
    class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }

        Node(Node left, Node right) {
            this.left = left;
            this.right = right;
            this.val = this.left.val + this.right.val;
        }

        Node construct(int[] A, int l, int r) {
            if (l == r)
                return new Node(A[l]);
            int mid = l + (r - l) / 2;
            return new Node(construct(A, l, mid), construct(A, mid + 1, r));
        }

        int sumForRange(int start, int end, int l, int r) {
            if (start <= l && r <= end)
                return this.val;
            if (end < l || r < start)
                return 0;
            int mid = l + (r - l) / 2;
            int lSum = 0, rSum = 0;
            if (this.left != null)
                lSum = sumForRange(start, end, l, mid);
            if (this.right != null)
                rSum = sumForRange(start, end, mid, r);
            return lSum + rSum;
        }
    }
}