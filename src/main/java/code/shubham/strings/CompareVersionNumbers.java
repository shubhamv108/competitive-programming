package code.shubham.strings;

public class CompareVersionNumbers {

    class Solution {
        public int compareVersion(String version1, String version2) {
            String[] v1 = version1.split("\\.");
            String[] v2 = version2.split("\\.");

            int i = 0, maxIdx = Math.min(v1.length, v2.length);
            for (; i < maxIdx; ++i) {
                int a = Integer.parseInt(v1[i]);
                int b = Integer.parseInt(v2[i]);
                if (a == b)
                    continue;

                return a < b ? -1 : 1;
            }

            while (i < v1.length) {
                int a = Integer.parseInt(v1[i]);
                if (a > 0)
                    return 1;
                ++i;
            }

            while (i < v2.length) {
                int a = Integer.parseInt(v2[i]);
                if (a > 0)
                    return -1;
                ++i;
            }

            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(new CompareVersionNumbers().new Solution().compareVersion("0.1", "1.1"));
    }

}
