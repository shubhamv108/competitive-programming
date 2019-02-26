package code.linkedlist;

import java.util.Scanner;

public class SumOfTwoNumbers {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        LinkedListFunctions llf = new LinkedListFunctions();
        while(t--!=0)
        {
            LLNode head1 = null;
            LLNode head2 = null;
            LLNode head3 = null;
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            head1=llf.ReadLinkedList(head1,n1);
            head2=llf.ReadLinkedList(head2,n2);
            head3=llf.sumOfTwoNumbers(head1,head2);
            llf.printListWithoutSpace(head3);
        }

    }
}
