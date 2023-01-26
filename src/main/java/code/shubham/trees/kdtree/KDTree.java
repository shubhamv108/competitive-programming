package code.shubham.trees.kdtree;

import java.util.Arrays;

public class KDTree {
    private final int k;
    private Node root;

    private final Node DEFAULT_MINIMUM;

    private class Node {
        private int[] point;
        private Node left, right;

        private Node(int[] point) {
            this.point = point;
        }

        private void insert(int[] point, int dimensionIndex) {
            if (point[dimensionIndex] < this.point[dimensionIndex]) {
                if (this.left == null) {
                    this.left = new Node(point);
                    return;
                }
                this.left.insert(point, (dimensionIndex + 1) % KDTree.this.k);
                return;
            }
            if (this.right == null) {
                this.right = new Node(point);
                return;
            }
            this.right.insert(point, (dimensionIndex + 1) % KDTree.this.k);
        }

        private boolean search(int[] point, int dimensionIndex) {
            if (PointUtils.areEqual(this.point, point, KDTree.this.k))
                return true;

            if (point[dimensionIndex] < this.point[dimensionIndex]) {
                if (this.left == null)
                    return false;
                return this.left.search(point, (dimensionIndex + 1) % KDTree.this.k);
            }
            if (this.right == null)
                return false;
            return this.right.search(point, (dimensionIndex + 1) % KDTree.this.k);
        }

        private Node findMinimum(int dimension, int dimensionIndex) {
            if (dimension == dimensionIndex) {
                if (this.left == null)
                    return this;
                return this.min(
                        this,
                        this.left.findMinimum(dimension, (dimensionIndex + 1) % KDTree.this.k),
                        dimension);
            }

            Node l = null, r = null;
            if (this.left != null)
                l = this.left.findMinimum(dimension, (dimensionIndex + 1) % KDTree.this.k);
            if (this.right != null)
                r = this.right.findMinimum(dimension, (dimensionIndex + 1) % KDTree.this.k);

            return this.min(this, min(l, r, dimension), dimension);
        }

        private Node min(Node A, Node B, int dimension) {
            if (A == null && B == null)
                return KDTree.this.DEFAULT_MINIMUM;
            if (A == null)
                return B;
            if (B == null)
                return A;
            if (A.point[dimension] < B.point[dimension])
                return A;
            return B;
        }

        Node delete(int[] point, int dimensionIndex) {
            if (PointUtils.areEqual(this.point, point, KDTree.this.k)) {
                if (this.left == null && this.right == null)
                    return null;
                if (this.right != null) {
                    Node min = this.right.findMinimum(dimensionIndex, dimensionIndex);
                    PointUtils.copyAToB(min.point, this.point, KDTree.this.k);
                    this.right = this.right.delete(point, (dimensionIndex + 1) % KDTree.this.k);
                } else if (this.left != null) {
                    Node min = this.right.findMinimum(dimensionIndex, dimensionIndex);
                    PointUtils.copyAToB(min.point, this.point, KDTree.this.k);
                    this.right = this.right.delete(point, (dimensionIndex + 1) % KDTree.this.k);
                }
                return this;
            }

            if (point[dimensionIndex] < this.point[dimensionIndex])
                this.left = delete(point, (dimensionIndex + 1) % KDTree.this.k);
            else
                this.right = delete(point, (dimensionIndex + 1) % KDTree.this.k);
            return this;
        }
    }

    private class PointUtils {
        static boolean areEqual(int[] A, int[] B, int k) {
            for (int i = 0; i < k; i++)
                if (A[i] != B[i])
                    return false;
            return true;
        }

        static void copyAToB(int[] A, int[] B, int k) {
            for (int i = 0; i < k; i++)
                A[i] = B[i];
        }
    }

    public KDTree(int k) {
        this.k = k;
        int[] o =  new int[k];
        Arrays.fill(o, Integer.MAX_VALUE);
        this.DEFAULT_MINIMUM = new Node(o);
    }

    public void insert(int[] point) {
        if (this.root == null) {
            this.root = new Node(point);
            return;
        }
        this.root.insert(point, 0);
    }

    public boolean search(int[] point) {
        if (this.root == null)
            return false;
        return this.root.search(point, 0);
    }

    public void delete(int[] point) {
        if (this.root == null)
            return;
        this.root = this.root.delete(point, 0);
    }

    public Integer findMinimum(int dimension) {
        if (root == null)
            return null;
        return this.root.findMinimum(dimension, 0).point[dimension];
    }
}
