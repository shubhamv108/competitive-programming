package geeksforgeeks.contribution;

public class ReverseListInGroupsOfSizeK {

    // Linked list node
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
        }
    }

    // Function to reverse the linked list in groups of
    //size k and return the pointer to the new head node. /
    static Node reverse(Node head, int K ) {

        if (K <= 1) return head;

        Node curr = head;

        /* curHead pointer keeps track of the head of the
        current k-reverse linked list */
        Node curHead = null;

        /* curTail pointer keeps track of the tail of the
        current k-reverse linked list */
        Node curTail = curr;

        /* ResultTail pointer keeps track of the last node
        of the k-reversed linked list */
        Node resultTail = null;

        Node resultHead = null;

        Node temp;

        int t = K;

        // Traverse till the end of the linked list
        while (curr != null) {
            t--;
            temp = curr.next;
            curr.next = curHead;
            curHead = curr;
            curr = temp;

            if (curr == null || t == 0) {

                // Sets the new head of the input list
                if (resultHead == null)
                    resultHead = curHead;

                /* ResultTail pointer keeps track of the last node
                of the k-reversed linked list. We join the
                ResultTail pointer with the head of the next
                k-reversed linked list's head */
                if (resultTail != null)
                    resultTail.next = curHead;


                curTail.next = curr;

                t = K;

                /* The tail is then updated to the last node
                of the next k-reverse linked list */
                resultTail = curTail;

                // breaking execution head is null
                if (curr == null) break;

                /* Making head as the tail pointer to next
                k-reverse linked list*/
                curTail = curr;
            }
        }

        // resultHead is new head of the input list /
        return resultHead;
    }

    // Function to print the linked list
    static void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    // Function to form a new linked list from input array
    static Node createAndGetLinkedList (int... n) {
        if (n.length == 0) return null;
        Node node = new Node(n[0]);
        Node l = node;
        for (int i = 1; i < n.length; i++) {
            l.next = new Node(n[i]);
            l = l.next;
        }
        return node;
    }

    public static void main(String[] args) {

        Node head = createAndGetLinkedList( 1, 2, 3, 4, 5, 6, 7, 8);

        int K = 3;

        System.out.print( "Given linked list \n");
        printList(head);
        head = reverse(head, K);

        System.out.print( "\nReversed Linked list \n");
        printList(head);

    }

}
