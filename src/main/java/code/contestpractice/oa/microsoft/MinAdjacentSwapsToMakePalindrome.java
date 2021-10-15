package code.contestpractice.oa.microsoft;

public class MinAdjacentSwapsToMakePalindrome {

    class Solution {
        int solve(String s) {
            char[] chrs = s.toCharArray();
            if (!this.isShuffledPalindrome(chrs)) return -1;
            int l = 0, r = chrs.length - 1, result = 0;
            while (l <= r) {
                if (chrs[l] != chrs[r]) {
                    int k = r;
                    while (k > l && chrs[k] != chrs[l]) k--;
                    if (k == l) {
                        swap(chrs, l, l+1);
                        result++;
                    } else {
                        while (k < r) {
                            swap(chrs, k, k+1);
                            result++;
                            k++;
                        }
                        l++;
                        r--;
                    }
                } else {
                    l++; r--;
                }
            }
            return result;
        }

        boolean isShuffledPalindrome(char[] A) {
            int[] freq = new int[26];
            for (char c : A) freq[c - 'a']++;
            int evenCount, oddCount;
            evenCount = oddCount = 0;
            for (int count : freq)
                if ((count & 1) == 0)
                    evenCount++;
                else
                    oddCount++;
            if (((A.length & 1) == 0) && oddCount == 0) return true;
            if ((A.length & 1) == 1 && oddCount == 1) return true;
            return false;
        }

        void swap(char[] A, int i , int j) {
            char t = A[i];
            A[i] = A[j];
            A[j] = t;
        }
    }

    public static void main(String[] args) {
        MinAdjacentSwapsToMakePalindrome minAdjacentSwapsToMakePalindrome = new MinAdjacentSwapsToMakePalindrome();
        Solution solution = minAdjacentSwapsToMakePalindrome.new Solution();
        int swapCount = solution.solve("ntiin");
        System.out.println(swapCount);
    }

}
