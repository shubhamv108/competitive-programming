package code.shubham.binarysearch;

public class PainterPartitionProblem {
    class Solution {
        int mod = (int) 1e7 + 3;
        public int paint(int A, int B, int[] C) {
            long r = 0, l = 0;
            for (int c : C) {
                r += c;
                l = Math.max(l, c);
            }
            l %= mod;
            while (l < r) {
                long mid = l + (r - l) / 2;
                int painters = countOfPainters(C, mid);
                if (painters <= A)
                    r = mid;
                else
                    l = mid + 1;
            }
            return (int) (l * B % mod);
        }

        int countOfPainters(int[] C, long contigousBoardSize) {
            int count = 1;
            long sum = 0;
            for (int c : C) {
                sum += c;
                if (sum > contigousBoardSize) {
                    sum = c;
                    count++;
                }
            }
            return count;
        }
    }

    class Solution2 {
        int getMax(int arr[], int n) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++)
                if (arr[i] > max)
                    max = arr[i];
            return max;
        }

        int getSum(int arr[], int n) {
            int total = 0;
            for (int i = 0; i < n; i++)
                total += arr[i];
            return total;
        }

        int numberOfPainters(int arr[], int n, int maxLen) {
            int total = 0, numPainters = 1;
            for (int i = 0; i < n; i++) {
                total += arr[i];
                if (total > maxLen) {
                    total = arr[i];
                    numPainters++;
                }
            }
            return numPainters;
        }

        int partition(int arr[], int n, int k) {
            int lo = getMax(arr, n);
            int hi = getSum(arr, n);
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                int requiredPainters = numberOfPainters(arr, n, mid);
                if (requiredPainters <= k)
                    hi = mid;
                else
                    lo = mid + 1;
            }
            return lo;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new PainterPartitionProblem()
                        .new Solution()
                        .paint(1, 1000000, new int[] { 1000000, 1000000 })
        );
    }

}
