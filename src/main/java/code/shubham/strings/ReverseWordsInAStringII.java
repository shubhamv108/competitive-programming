package code.shubham.strings;

public class ReverseWordsInAStringII {
    public class Solution {
        public String reverseWords(String s) {
            char[] A = s.toCharArray();
            int startIdx = 0;
            for (int i = 0; i < s.length(); ++i) {
                if (A[i] == 32) {
                    reverse(A, startIdx, i-1);
                    startIdx = i+1;
                }
            }
            reverse(A, startIdx, A.length - 1);
            reverse(A, 0, A.length - 1);
            return new String(A);
        }

        void reverse(char[] A, int l, int r) {
            while (l < r) {
                char t = A[l];
                A[l++] = A[r];
                A[r--] = t;
            }
        }
    }
}
