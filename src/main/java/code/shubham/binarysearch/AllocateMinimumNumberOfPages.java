package code.shubham.binarysearch;

public class AllocateMinimumNumberOfPages {

    public class Solution {
        /**
         *
         * @param A unsorted arrays
         * @param B number of students
         * @return
         */
        public int books(int[] A, int B) {
            if (B > A.length) return -1;
            int sum = 0, max = A[0];
            for (int a : A) {
                max = Math.max(max, a);
                sum += a;
            }

            // System.out.println(sum + "   " + max);
            int l = max, r = sum, result = l;
            while (l <= r) {
                int m = l + ((r - l) / 2);
                if (isPossible(A, B, m)) {
                    result = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return result;
        }

        boolean isPossible(int[] A, int B, int m) {
            int curSum = 0, count = 1;
            for (int a : A) {
                if (a > m) return false;
                if (a + curSum > m) {
                    count++;
                    curSum = a;
                    if (count > B) return false;
                } else {
                    curSum += a;
                }
            }
            return true;
        }
    }

    class Solution2 {
        /**
         *
         * @param A sorted array
         * @param B number of students
         * @return
         */
        public int books(int[] A, int B) {
            int len = A.length;
            if (B > len) return -1;
            int prefixSum[] = new int[len];
            prefixSum[0] = A[0];
            for (int i = 1; i < len; i++)
                prefixSum[i] = A[i] + prefixSum[1];

            int l = A[len - 1], r = prefixSum[len - 1], result = r;
            while (l <= r) {
                int m = l + ((r - l) / 2);
                if (true/*isPossible(prefixSum, B, m)*/) {
                    result = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return result;
        }

//        boolean isPossible(int[] prefixSum, int B, int m) {
//            int curSum = 0, count = 1;
//            int l = 0, r = prefixSum.length - 1;
//            while (l <= r) {
//                int mid = l + (r - l) / 2;
//                if (prefixSum[mid] == m) {
//                    break;
//                } if (prefixSum[mid - 1] < m && m < prefixSum[mid]) {
//                    break;
//                } else if (prefixSum[mid] > m) {
//                    r = mid - 1;
//                } else {
//                    l = mid + 1;
//                }
//            }
//
//            for (int i = 0; i < prefixSum.length; i++) {
//                if (prefixSum[i] > m) return false;
//                if (a + curSum > m) {
//                    count++;
//                    curSum = a;
//                    if (count > B) return false;
//                } else {
//                    curSum += a;
//                }
//            }
//            return true;
//        }
    }

    public static void main(String[] args) {
        System.out.println(
                new AllocateMinimumNumberOfPages().new Solution().books(
                        new int[] { 12, 34, 67, 90 }, 2
                )
        );
    }

}
