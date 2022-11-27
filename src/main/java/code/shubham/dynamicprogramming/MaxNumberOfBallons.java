package code.shubham.dynamicprogramming;

public class MaxNumberOfBallons {

    class Solution {
        public int maxNumberOfBalloons(String textString) {
            int result = Integer.MAX_VALUE;
            char[] pattern = "balloon".toCharArray();
            char[] text = textString.toCharArray();
            int freqInText[] = new int[26];
            int freqInPattern[] = new int[26];
            for (int i = 0; i < pattern.length; i++)
                freqInPattern[pattern[i]-'a']++;
            for (int i = 0; i < text.length; i++)
                freqInText[text[i]-'a']++;
            for (int i = 0; i < 26; i++)
                if (freqInPattern[i] > 0)
                    result = Math.min(result, freqInText[i]/freqInPattern[i]);
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MaxNumberOfBallons().new Solution().maxNumberOfBalloons("loonbalxballpoon")
        );
    }

}
