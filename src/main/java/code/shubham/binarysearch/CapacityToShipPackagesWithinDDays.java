package code.shubham.binarysearch;

public class CapacityToShipPackagesWithinDDays {
    class Solution {
        public int shipWithinDays(int[] A, int days) {
            int max = 0;
            int sum = 0;
            for (int a : A) {
                max = Math.max(max, a);
                sum += a;
            }

            int l = Math.max(max, (int) Math.floor((double)sum / days));
            int r = max * (int) Math.ceil((double) A.length / days);
            int result = 0;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (can(A, m, days)) {
                    result = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return result;
        }

        boolean can(int[] A, int c, int t) {
            int cur = 0, days = 1;
            for (int a : A) {
                if (cur + a > c) {
                    ++days;
                    if (days > t)
                        return false;
                    cur = 0;
                }
                cur += a;
            }
            return days <= t;
        }

    }

    void main() {
        System.out.println(new Solution().shipWithinDays(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },   5));
    }
}
