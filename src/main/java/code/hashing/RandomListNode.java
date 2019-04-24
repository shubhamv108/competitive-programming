package hashing;

public class RandomListNode {
     public int label;
     public RandomListNode next, random;
     public RandomListNode(int x) { this.label = x; }

     public void print() {
          RandomListNode n = this;
          while (n.next != null) {
               System.out.print(n.label + "->");
               n = n.next;
          }
          System.out.println(n.label);
     }
}
