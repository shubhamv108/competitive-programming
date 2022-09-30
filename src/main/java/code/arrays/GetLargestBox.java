package code.arrays;

import java.util.Arrays;

public class GetLargestBox {
    public class Solution {

        int getLargestRectangle(String s, int[] x, int[] y) {
            String[] a = s.split(",");
            return getLargestRectangle(new int[] { Integer.valueOf(a[0]), Integer.valueOf(a[1]) }, x, y);
        }

        int getLargestRectangle(int[] S, int[] x, int[] y) {
            Arrays.sort(x);
            Arrays.sort(y);
            if (x.length == 0 && y.length == 0)
                return S[0] * S[1];

            int prevX = 0, prevY = 0, result = 0, xLength = 0;

            if (x.length != 0) {
                for (int i = 0; i < x.length; i++) {
                    xLength = x[i] - prevX;
                    prevY = 0;
                    for (int j = 0; j < y.length; j++) {
                        result = Math.max((xLength) * (y[j] - prevY), result);
                        prevY = y[j];
                    }
                    prevX = x[i];
                }
            } else {
                prevY = 0;
                for (int j = 0; j < y.length; j++) {
                    result = Math.max(S[0] * (y[j] - prevY), result);
                    prevY = y[j];
                }
            }

            result = Math.max((S[0] - prevX) * (S[1] - prevY), result);

            return result;
        }
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        System.out.println(
        new GetLargestBox().new Solution().
                getLargestRectangle("100,70", new int[] {25}, new int[] {}));
    }
}
