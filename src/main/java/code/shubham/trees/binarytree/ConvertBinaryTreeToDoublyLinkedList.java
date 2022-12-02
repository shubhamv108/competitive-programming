package code.shubham.trees.binarytree;

public class ConvertBinaryTreeToDoublyLinkedList {
    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "" + val;
        }

        void printList() {
            System.out.print(this);
            if (this.right == null)
                return;

            System.out.print("<=>");
            this.right.printList();
        }
    }

    class Solution {
        Node head, prev;
        Node solve(Node root) {
            convert(root);
            return head;
        }

        void convert(Node root) {
            if (root == null)
                return;

            convert(root.left);
            if (head == null)
                head = root;
            else {
                root.left = prev;
                prev.right = root;
            }
            prev = root;
            convert(root.right);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(12);
        root.right = new Node(15);
        root.left.left = new Node(25);
        root.left.right = new Node(30);
        root.right.left = new Node(36);

        Solution solution = new ConvertBinaryTreeToDoublyLinkedList().new Solution();
        Node head = solution.solve(root);
        head.printList();
    }
}
