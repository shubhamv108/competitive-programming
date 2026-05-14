package code.shubham.strings.frequency;

public class MirrorFrequency {
    class Solution {
        public int mirrorFrequency(String s) {
            int n = s.length(), result = 0;
            int[] f = new int[123];
            for (int i = 0; i < n; ++i)
                ++f[s.charAt(i)];

            for (int i = 48; i <= 52; ++i)
                result += Math.abs(f[i] - f[57 - (i - 48)]);

            for (int i = 97; i <= 109; ++i)
                result += Math.abs(f[i] - f[122 - (i - 97)]);

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MirrorFrequency().new Solution().mirrorFrequency("ab1z9"));
        System.out.println(new MirrorFrequency().new Solution().mirrorFrequency("4m7n"));
        System.out.println(new MirrorFrequency().new Solution().mirrorFrequency("byby"));
    }
}