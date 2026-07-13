package code.shubham.graphs.trees.bsttree.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

        void printAscending() {
            LinkedList<Node> stack = new LinkedList();
            Node cur = this;
            while (!stack.isEmpty() || cur != null) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }

                cur = stack.pop();
                System.out.print(cur.val + " -> ");
                cur = cur.right;
            }
        }

        void printDescending() {
            LinkedList<Node> stack = new LinkedList();
            Node cur = this;
            while (!stack.isEmpty() || cur != null) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.right;
                }

                cur = stack.pop();
                System.out.print(cur.val + " -> ");
                cur = cur.left;
            }
        }

        void printPreorder() {
            LinkedList<Node> stack = new LinkedList();
            Node cur = this;
            while (!stack.isEmpty() || cur != null) {
                while (cur != null) {
                    System.out.print(cur.val + " -> ");
                    stack.push(cur);
                    cur = cur.left;
                }

                cur = stack.pop();
                cur = cur.right;
            }
        }

        void printPostorder() {
            LinkedList<Node> stack = new LinkedList();
            Node cur = this;
            while (!stack.isEmpty() || cur != null) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }

                cur = stack.peek();
                if (cur.right != null)
                    cur = cur.right;
                else {
                    System.out.println(cur.val + "-.");

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

    void main() {
        Node n = new Node(24);
        n.right = new Node(35);
        n.right.left = new Node(31);
        n.right.right = new Node(42);
        n.left = new Node(12);
        n.left.left = new Node(10);
        n.left.right = new Node(15);
        n.printAscending();
        System.out.println();
        n.printDescending();
        System.out.println();
        n.printPreorder();
        System.out.println();
        n.printPostorder();
    }

}
