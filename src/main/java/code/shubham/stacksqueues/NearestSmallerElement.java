package code.shubham.stacksqueues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class NearestSmallerElement {
    class Solution {
        public ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {
            ArrayList<Integer> result = new ArrayList<>();
            Stack<Integer> s = new Stack<>();
            s.push(A.get(0));
            result.add(-1);
            for (int i = 1; i < A.size(); i++) {
                while (!s.isEmpty() && A.get(i) <= s.peek()) s.pop();
                if (s.isEmpty()) result.add(-1);
                else result.add(s.peek());
                s.push(A.get(i));
            }
            return result;
        }
    }

    public static void main(String[] args) {
        java.util.LinkedList l = new java.util.LinkedList();
        l.add(2);
        l.add(5);
        System.out.println(l);
        String a = "Hello World!";
        String b = a.substring(6, 12) + a.substring(12, 6);
        System.out.println(b);
        new NearestSmallerElement().
                new Solution().
                prevSmaller(new ArrayList<>(Arrays.asList(4, 5, 2, 10, 8))).
                stream().
                forEach(e -> System.out.print(e + " "));
    }
}
