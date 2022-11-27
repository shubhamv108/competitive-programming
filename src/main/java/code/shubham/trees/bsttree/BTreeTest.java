package code.shubham.trees.bsttree;

import code.shubham.trees.BTNode;

public class BTreeTest {
    public static void main(String[] args) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(7);
        root.left.left = new BTNode(3);
        root.left.right = new BTNode(9);
        root.right = new BTNode(2);
        root.right.right = new BTNode(4);
        root.right.right.left = new BTNode(6);
        root.right.right.right = new BTNode(5);
        BTree bTree = new BTree();
        Runnable r = () -> bTree.postorderIteratively(root);
        new Thread(r).start();
        bTree.postorderIteratively(root);

    }
}
