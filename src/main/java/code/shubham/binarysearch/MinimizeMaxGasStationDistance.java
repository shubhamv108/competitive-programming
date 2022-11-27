package code.shubham.binarysearch;

public class MinimizeMaxGasStationDistance {

    class Solution {
        public double minmaxGasDist(int[] stations, int k) {
            int len = stations.length;
            double left = 0, right = stations[len - 1] - stations[0];

            while (right - left > 1e-6) {
                double mid = (left + right) / 2;
                if (possible(stations, len, mid, k)) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            return right;
        }

        boolean possible(int[] stations, int len, double mid, int k) {
            int count = 0;
            for (int i = 0; i < len - 1; i++)
                count += Math.ceil((stations[i + 1] - stations[i]) / mid) - 1;
            return count <= k;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MinimizeMaxGasStationDistance().new Solution().minmaxGasDist(
                        new int[] { 3, 6, 12, 19, 33, 44, 67, 72, 89, 95 }, 2
                )
        );
    }
}
