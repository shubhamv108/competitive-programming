package code.strings;

import java.util.stream.IntStream;

public class ReverseSentence {

    static class Solution {
        char[] A;

        Solution(char[] A) {
            this.A = A;
        }

        void solve() {
            reverseIndexRange(0, A.length - 1);

            int startIdx = 0;
            for (int i = 0; i <= A.length; i++) {
                if (i == A.length || A[i] == ' ') {
                    reverseIndexRange(startIdx, i-1);
                    startIdx = i+1;
                }
            }
        }

        void reverseIndexRange(int start, int end) {
            char t;
            while (start < end) {
                t = A[start];
                A[start] = A[end];
                A[end] = t;
                start++;
                end--;
            }
        }
    }

    public static void main(String[] args) {
        char[] sentence = new char[] { 'a', 'b', ' ', 'c', 'd' };
        new ReverseSentence.Solution(sentence).solve();
        IntStream.range(0, sentence.length).forEach(i -> System.out.print(sentence[i]));
    }

}
