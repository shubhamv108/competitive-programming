package code.shubham.stack;

import java.util.LinkedList;
import java.util.List;

public class MergeTwoAdjacentElements {
    class Solution {
        public List<Long> mergeAdjacent(int[] A) {
            LinkedList<Long> s = new LinkedList<>();
            for (long a : A) {
                while (!s.isEmpty() && s.peekLast() == a) {
                    s.removeLast();
                    a <<= 1;
                }
                s.offer(a);
            }

            return s;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MergeTwoAdjacentElements().new Solution().mergeAdjacent(new int[] { 3, 7, 5}));
        System.out.println(new MergeTwoAdjacentElements().new Solution().mergeAdjacent(new int[] { 3, 1, 1, 4 }));
        System.out.println(new MergeTwoAdjacentElements().new Solution().mergeAdjacent(new int[] { 2, 1, 1, 4 }));
        System.out.println(new MergeTwoAdjacentElements().new Solution().mergeAdjacent(new int[] { 2, 1, 1, 2 }));
        System.out.println(new MergeTwoAdjacentElements().new Solution().mergeAdjacent(new int[] { 2, 2, 4 }));
    }
}
