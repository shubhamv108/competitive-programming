package code.arrays;

public class MaxSumNonOverlappingSubArrays {

    static void calculatePresumArray(int presum[],
                                     int arr[],
                                     int n, int k) {
        for (int i = 0; i < k; i++)
            presum[0] += arr[i];
        for (int i = 1; i <= n - k; i++)
            presum[i] += presum[i - 1] + arr[i + k - 1] -
                    arr[i - 1];
    }

    static int maxSumMNonOverlappingSubarray(int presum[],
                                             int m, int size,
                                             int k, int start) {
        if (m == 0) return 0;
        if (start > size - 1) return 0;

        int mx = 0;
        int includeMax = presum[start] +
                maxSumMNonOverlappingSubarray(presum,
                        m - 1, size, k, start + k);
        int excludeMax =
                maxSumMNonOverlappingSubarray(presum,
                        m, size, k, start + 1);
        return Math.max(includeMax, excludeMax);
    }

    public static void main(String[] args) {

    }

}
