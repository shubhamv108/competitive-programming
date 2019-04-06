package code.stacksqueues;

import java.util.*;
import java.util.stream.IntStream;

public class NearestLargerElement {
    class Solution {
        public ArrayList<Integer> nextLarger (ArrayList<Integer> A) {
            ArrayList<Integer> result = new ArrayList<>();
            IntStream.range(0, A.size()).forEach(i-> result.add(null));
            Stack<Integer> s = new Stack<>();
            for (int i = A.size() - 1; i > -1; i--) {
                while (!s.isEmpty() && A.get(i) >= s.peek()) s.pop();
                if (s.isEmpty()) result.set(i, -1);
                else result.set(i, s.peek());
                s.push(A.get(i));
            }
            return result;
        }
    }

    public static void main(String[] args) {
        new NearestLargerElement().
                new Solution().
                nextLarger(new ArrayList<>(Arrays.asList(100, 2, 1, 5, 2, 10, 8, 11))).
                stream().
                forEach(e -> System.out.print(e + " "));
    }
}