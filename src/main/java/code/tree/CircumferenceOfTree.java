package code.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CircumferenceOfTree {

    static class Node {
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
        }
    }

    void printLeft(Node root) {
        if (root == null) return;
        if (root.left != null) {
            System.out.println(root.value + " ");
            printLeft(root.left);
        }
        else if (root.right != null) {
            System.out.println(root.value + " ");
            printLeft(root.right);
        }
    }

    void printRight(Node root) {
        if (root == null) return;
        if (root.right != null) {
            printRight(root.right);
            System.out.println(root.value + " ");
        }
        else if (root.left != null) {
            printRight(root.left);
            System.out.println(root.value + " ");
        }
    }

    void printLeaf(Node root) {
        if (root == null) return;
        printLeaf(root.left);
        if (root.left == null && root.right == null) {
            System.out.println(root.value + " ");
        }
        printLeaf(root.right);
    }

    void printCircumference(Node root) {
        printLeft(root);
        printLeaf(root);
        printRight(root);
    }

    public static void main(String[] args) {
        Node root = new Node(0);
        root.left = new Node(1);
        root.right = new Node(2);
        root.left.right = new Node(3);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        root.right.left.left = new Node(7);
        root.right.left.right = new Node(8);
        new CircumferenceOfTree().printCircumference(root);
    }

}
