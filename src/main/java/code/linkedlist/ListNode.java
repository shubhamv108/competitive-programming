package code.linkedlist;

public class ListNode {

    public int val;
    public ListNode next;
    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void printList() {
        System.out.print(this.toString());
        if (this.next != null) {
            System.out.print(", ");
            this.next.printList();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}