package code.shubham.arrays;

public class NumberOfPositiveProductSubarrays {
    class Solution {
        int numberOfSubarraysWithNegativeProduct(int[] A) {
            int n = A.length, pos = 0, neg = 0;
            for (int i = 0; i < n; ++i) {
                A[i] = A[i] > 0 ? 1 : -1;
                if (i > 0)
                    A[i] *= A[i-1];
                int tt = A[i] > 1 ? ++pos : ++neg;
            }
            return pos * neg;
        }

        int numberOfSubarraysWithPositiveProduct(int[] A) {
            int n = A.length;
            int total = (n * (n-1)) / 2;
            return total - numberOfSubarraysWithNegativeProduct(A);
        }
    }

    class Solution2 {
        public long numberOfSubarraysWithPositiveProduct(int[] nums) {
            long answer = 0;
            long pos = 0, neg = 0;

            for (int num : nums) {
                if (num == 0) {
                    pos = 0;
                    neg = 0;
                } else if (num > 0) {
                    long newPos = pos + 1;
                    long newNeg = (neg > 0) ? neg + 1 : 0;
                    pos = newPos;
                    neg = newNeg;
                } else {
                    long newPos = (neg > 0) ? neg + 1 : 0;
                    long newNeg = pos + 1;
                    pos = newPos;
                    neg = newNeg;
                }
                answer += pos;
            }
            return answer;
        }
    }

    void main() {
        System.out.println(new Solution2().numberOfSubarraysWithPositiveProduct(new int[] { -1, 0, -1, 2, -3, 4 }));
    }
}
