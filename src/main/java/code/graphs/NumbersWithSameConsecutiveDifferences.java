package code.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class NumbersWithSameConsecutiveDifferences {
    // Time & Space: O(2^N)
    class Solution {
        public int[] numsSameConsecDiff(int n, int k) {
            if (n == 1)
                return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

            ArrayList<Integer> result = new ArrayList<>();
            for (int num = 1; num < 10; num++)
                this.recurse(n - 1, k, num, result);

            return result.stream().mapToInt(i -> i).toArray();
        }

        void recurse(int n, int k, int num, ArrayList<Integer> result) {
            if (n == 0) {
                result.add(num);
                return;
            }

            int lastDigit = num % 10;
            int next = lastDigit - k;
            if (next > -1 && next < 10)
                recurse(n-1, k, (num * 10) + next, result);

            if (k != 0) {
                next = lastDigit + k;
                if (next > -1 && next < 10)
                    recurse(n-1, k, (num * 10) + next, result);
            }
        }
    }

    // Time & Space: O(2^N)
    class Solution2 {
        public int[] numsSameConsecDiff(int n, int k) {
            if (n == 1)
                return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

            ArrayList<Integer> result = new ArrayList<>();
            LinkedList<Integer> q = new LinkedList<>();

            int m, size, p, lastDigit, next;
            for (int num = 1; num < 10; num++) {
                q.offer(num);
                m = n - 1;

                while (!q.isEmpty()) {
                    size = q.size();
                    while (size-- > 0) {
                        p = q.poll();

                        if (m == 0) {
                            result.add(p);
                            continue;
                        }

                        lastDigit = p % 10;

                        next = lastDigit + k;
                        if (next > -1 && next < 10)
                            q.offer( (p * 10) + next );

                        if (k != 0) {
                            next = lastDigit - k;
                            if (next > -1 && next < 10)
                                q.offer( (p * 10) + next );
                        }
                    }
                    m--;
                }
            }

            return result.stream().mapToInt(i -> i).toArray();
        }
    }

    class Solution3 {
        public int[] numsSameConsecDiff(int n, int k) {
            if (n == 1)
                return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

            ArrayList<Integer> result = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)), newResult;
            n--;

            int lastDigit, next;
            while (n-- > 0) {
                newResult = new ArrayList<>();

                for (int num : result) {
                    lastDigit = num % 10;

                    if ((lastDigit + k) < 10)
                        newResult.add((num * 10) + (lastDigit + k));

                    if (k != 0 && (lastDigit - k) > -1)
                        newResult.add((num * 10) + (lastDigit - k));
                }
                result = newResult;
            }

            return result.stream().mapToInt(i -> i).toArray();
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new NumbersWithSameConsecutiveDifferences().new Solution3().numsSameConsecDiff(2, 0)
        );
    }
}
