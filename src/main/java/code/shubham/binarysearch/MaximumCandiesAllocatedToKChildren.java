package code.shubham.binarysearch;

public class MaximumCandiesAllocatedToKChildren {
    class Solution {
        public int maximumCandies(int[] A, long k) {
            int t = 0, max = 0;
            for (int a : A) {
                t += a;
                max = Math.max(max, a);
            }

            if (t < k)
                return 0;
            int l = 1, r = max, result = -1;
            while (l <= r) {
                int m = l + ((r - l) >> 1);

                long sum = 0l;
                for (int a : A)
                    sum += (a / m);

                if (k > sum)
                    r = m - 1;
                else {
                    result = l;
                    l = m + 1;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaximumCandiesAllocatedToKChildren().new Solution().maximumCandies(new int[] {55896,700114,762208,517116,470962,787190,708180,24888,444935,476075,826061,160591,360388,357064,220175,839971,517082,235809,913563,767398,286497,785904,959290,300367,495107,221577,886298,503416,305638,3741,21801,788612,614268,77036,169241,732637,550418,985680,317107,312890,865561,289441,339978,248258,153290,129844,175623,847065,596515,167794,543724,592011,551669,273830,217715,513049,917526,749907,410517,103895,764290}, 3));
    }
}
