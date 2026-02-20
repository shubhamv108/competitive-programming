package code.shubham.strings;

public class FindTheClosestPalindrome {
    class Solution {
        public String nearestPalindromic(String A) {
            long number = Long.parseLong(A);
            if (number <= 10)
                    return String.valueOf(number - 1);
            if (number == 11)
                return "9";

            int n = A.length();
            long half = Long.parseLong(A.substring(0, (n + 1) / 2));

            long[] cand = new long[5];
            boolean isOddLength = (n & 1) == 1;
            cand[0] = palindrome(half - 1, isOddLength);
            cand[1] = palindrome(half, isOddLength);
            cand[2] = palindrome(half + 1, isOddLength);
            cand[3] = (long) Math.pow(10, n - 1) - 1;
            cand[4] = (long) Math.pow(10, n) + 1;

            long nearestPalindrome = 0;
            long minDifference = Long.MAX_VALUE;

            for (long candidate : cand) {
                if (candidate == number) continue;
                long difference = Math.abs(candidate - number);
                if (difference < minDifference || (difference == minDifference && candidate < nearestPalindrome)) {
                    minDifference = difference;
                    nearestPalindrome = candidate;
                }
            }

            return String.valueOf(nearestPalindrome);
        }

        private long palindrome(long A, boolean isOdd) {
            long a = A;
            if (isOdd)
                a /= 10;

            while (a > 0) {
                long d = a % 10;
                A = (A * 10) + d;
                a /= 10;
            }
            return A;
        }
    }

    class Solution1 {
        public String nearestPalindromic(String A) {
            int n = A.length();
            long AN = Long.valueOf(A);
            if (AN < 10)
                return String.valueOf(AN - 1);
            if (AN == 9)
                return String.valueOf(11);

            int midIdx = (n+1)/2;
            Long N = Long.valueOf(A.substring(0, midIdx));
            boolean isOdd = (n & 1) == 1;
            Long[] cands = new Long[5];
            cands[0] = reflect(N, isOdd);
            cands[1] = reflect(N+1, isOdd);
            cands[2] = reflect(N-1, isOdd);
            cands[3] = (long) Math.pow(10, n - 1) - 1;
            cands[4] = (long) Math.pow(10, n) + 1;

            long result = 0, minD = Long.MAX_VALUE;
            for (Long c : cands) {
                if (c == AN)
                    continue;
                long d = Math.abs(c - AN);
                if (d < minD || (d == minD && c < result)) {
                    result = c;
                    minD = d;
                }
            }
            return String.valueOf(result);
        }

        Long reflect(Long A, boolean isOdd) {
            Long a = A;
            if (isOdd) {
                a /=10;
            }
            while (a > 0) {
                long d = a % 10;
                A = (A * 10) + d;
                a /= 10;
            }
            return A;
        }
    }

    public static void main(String[] args) {
        System.out.println(new FindTheClosestPalindrome().new Solution1().nearestPalindromic("999"));
    }
}
