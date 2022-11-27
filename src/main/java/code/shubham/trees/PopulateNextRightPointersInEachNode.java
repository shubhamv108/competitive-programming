package code.shubham.trees;

public class PopulateNextRightPointersInEachNode {
    class Solution {
        class Node {
            public int val;
            public Node left;
            public Node right;
            public Node next;
            public Node() {}
            public Node(int _val) {
                val = _val;
            }
            public Node(int _val, Node _left, Node _right, Node _next) {
                val = _val;
                left = _left;
                right = _right;
                next = _next;
            }
        }

        public Node connect(Node root) {
            Node R = root;
            Node tempNode = new Node(0);
            while (root != null) {
                Node curNode = tempNode;
                while (root != null) {
                    if (root.left != null) {
                        curNode.next = root.left;
                        curNode = curNode.next;
                    }
                    if (root.right != null) {
                        curNode.next = root.right;
                        curNode = curNode.next;
                    }
                    root = root.next;
                }
                root = tempNode.next;
                tempNode.next = null;
            }
            return R;
        }
    }
}
