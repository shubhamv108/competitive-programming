package code.shubham.linkedlist;

public class IntersectionOfTwoLinkedList {

    private static class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }
        int length() {
            int count = 0;
            Node n = this;
            while (n != null) {
                count++;
                n = n.next;
            }
            return count;
        }
    }

    class Solution {
        Node solve(Node A, Node B) {
            int ALen = A.length();
            int BLen = B.length();
            if (ALen > BLen) {
                int count = ALen - BLen;
                while (count-- >  0)
                    A = A.next;
            }
            if (BLen > ALen) {
                int count = BLen - ALen;
                while (count-- >  0)
                    B = B.next;
            }
            while (A != null && A != B) {
                A  = A.next;
                B = B.next;
            }
            return A;
        }
    }

    public static void main(String[] args) {
        Node A = new Node(3);
        Node B = new Node(10);
        A.next = new Node(6);
        A.next.next = new Node(9);
        A.next.next = B.next = new Node(15);
        A.next.next.next = B.next.next = new Node(13);

        IntersectionOfTwoLinkedList intersectionOfTwoLinkedList = new IntersectionOfTwoLinkedList();
        Solution solution = intersectionOfTwoLinkedList.new Solution();
        System.out.println(solution.solve(A, B).val);
    }

}
