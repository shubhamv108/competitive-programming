package code.shubham.arrays;

public class LongestSubarrayOf1sAfterDeletingOneElement {

    class Solution {
        public int longestSubarray(int[] A) {
            int result = 0, oneF = 0, oneS = 0, other = 0;
            for (int a : A) {
                if (a == 1) {
                    if (other == 1) {
                        ++oneS;
                    } else {
                        ++oneF;
                    }
                    result = Math.max(result, oneF + oneS);
                } else {
                    ++other;
                }

                if (other == 2) {
                    oneF = oneS;
                    oneS = 0;
                    other = 1;
                }
            }

            if (A.length == result)
                --result;

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new LongestSubarrayOf1sAfterDeletingOneElement()
                    .new Solution()
                    .longestSubarray(new int[] { 0,1,1,1,0,1,1,0,1 })
        );
    }

}
