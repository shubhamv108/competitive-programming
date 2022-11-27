package code.shubham.linkedlist;

public class SumOfTwoNumbers {

    private static class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }

        void print() {
            Node t = this;
            while (t != null) {
                System.out.print(t.val + "->");
                t = t.next;
            }
            System.out.println();
        }
    }

    int length(Node node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    Node addNumbers(Node l1, Node l2) {
        int l1Len = length(l1);
        int l2Len = length(l2);
        if (l2Len > l1Len) {
            Node t = l1;
            l1 = l2;
            l2 = t;
            int temp = l1Len;
            l1Len = l2Len;
            l2Len = temp;
        }
        int n = l1Len - l2Len;
        Node sum = add(l1, l2, n);
        if (carry > 0) {
            Node newNode = new Node(carry);
            newNode.next = sum;
            sum = newNode;
        }
        return sum;
    }

    private int carry = 0;
    private Node add(Node l1, Node l2) {
        Node next = null;
        if (l1.next != null) {
            next = add(l1.next, l2.next);
        }
        int sum = l1.val + l2.val + carry;
        this.carry = sum / 10;
        Node newNode = new Node(sum%10);
        newNode.next = next;
        return newNode;
    }

    Node add(Node l1, Node l2, int n) {
        Node next = null;
        if (n > 0) {
            next = add(l1.next, l2, --n);
        } else {
            return add(l1, l2);
        }
        int sum = l1.val + carry;
        this.carry = sum / 10;
        Node newNode = new Node(sum%10);
        newNode.next = next;
        return newNode;
    }


    public static void main(String args[]) {
        Node l1 = new Node(1);
        l1.next = new Node(2);
        l1.next.next = new Node(3);
        Node l2 = new Node(9);
        l2.next = new Node(9);
        l2.next.next = new Node(9);
        new SumOfTwoNumbers().addNumbers(l1, l2).print();
    }
}
