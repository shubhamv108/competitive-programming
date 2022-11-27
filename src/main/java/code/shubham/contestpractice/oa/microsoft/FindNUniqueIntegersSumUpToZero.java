package code.shubham.contestpractice.oa.microsoft;

public class FindNUniqueIntegersSumUpToZero {class Solution {
    public int[] sumZero(int n) {
        int[] result = new int[n];
        int m = n/2, index = 0;
        for (int i = -m; i <= m; i++) {
            if (i == 0 && (n & 1) == 0)
                continue;
            result[index++] = i;
        }
        return result;
    }
}

}
