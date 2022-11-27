package code.shubham.randomcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class IteratorConfusion {

    private static class ListFor {
        public static void main (String[] args) {
            LinkedList<Integer> a = new LinkedList<>(Arrays.asList(1, 2, 3, 4));
            for (int i= 0; i<a.size(); i++) {
                if (a.get(i) == 2) {
                    // a.addFirst(5); // never ends
                    a.addLast(5);
                    a.remove(3);
                }
            }
            a.forEach(e -> System.out.println(e + " "));
        }
    }

    private static class ListIteration {
        public static void main (String[] args) {
            LinkedList<Integer> a = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 4));
            Iterator<Integer> itr = a.iterator();
            while (itr.hasNext()) {
                if (itr.next() == 3) {

                    itr.remove();
                }
            }
            a.forEach(e -> System.out.println(e + " "));
        }
    }

}
