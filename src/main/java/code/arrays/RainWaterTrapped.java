package code.arrays;

import java.util.Arrays;
import java.util.List;

public class RainWaterTrapped {
    class Solution {
        public int trap(List<Integer> A) {
            int l = 0;
            int r = A.size() - 1;
            int lm = 0;
            int rm = 0;
            int result = 0;
            // move towards middle from both ends
            while (l < r) {
                if (A.get(l) < A.get(r)) {
                    if   (lm < A.get(l)) lm = A.get(l++);
                    else result += lm - A.get(l++);
                } else {
                    if (rm < A.get(r)) rm = A.get(r--);
                    else result += rm - A.get(r--);
                }
            }
            return result;
        }
    }

    class Solution2 {
        public int trap(int[] height) {
            if (height == null) return 0;
            int len = height.length;
            if (len < 2) return 0;

            int l = 0, r = len - 1, lm = 0, rm = len - 1, result = 0;

            while (l < r) {
                if (height[l] < height[r]) {
                    if (height[l] > height[lm]) {
                        lm = l;
                        l++;
                    } else {
                        result += (height[lm] - height[l]);
                        l++;
                    }
                } else {
                    if (height[r] > height[rm]) {
                        rm = r;
                        r--;
                    } else {
                        result += (height[rm] - height[r]);
                        r--;
                    }
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new RainWaterTrapped().new Solution().trap(Arrays.asList(2, 0, 1, 0, 1, 0, 2, 3, 0, 1))
        );
    }

}
