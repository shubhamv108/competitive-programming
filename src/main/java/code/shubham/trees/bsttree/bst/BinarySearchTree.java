package code.shubham.trees.bsttree.bst;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

    private Node root;

    public void insert(int val) {
        this.root = insert(root, val);
    }

    public void delete(int val) {
        this.root = delete(root, val);
    }

    private static Node insert(Node node, int val) {
        if (node == null)
            return new Node(val);
        if (val < node.val)
            node.left = insert(node.left, val);
        else
            node.right = insert(node.right, val);
        return node;
    }

    private static Node delete(Node node, int val) {
        if (val < node.val)
            node.left = delete(node.left, val);
        else if (val > node.val)
            node.right = delete(node.right, val);
        else if (node.left == null)
            node = node.right;
        else if (node.right == null)
            node = node.left;
        else {
            node.val = node.right.inorderSuccessor();
            node.right = delete(node.right, val);
        }
        return node;
    }

    public void print() {
        root.print();
    }

    static class Node {
        int val;
        Node left;
        Node right;
        Node(int val) {
            this.val = val;
        }

        void print() {
            Queue<Node> q = new LinkedList<>();
            q.offer(this);
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    Node p = q.poll();
                    System.out.println(p.val + " ");
                    if (p.left != null)
                        q.offer(p.left);
                    if (p.right != null)
                        q.offer(p.right);
                }
            }
        }

        int inorderSuccessor() {
            Node n = this;
            while (n != null)
                n = n.left;
            return n.val;
        }
    }

}
