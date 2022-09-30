package code.arrays;

public class FindMissingPositive {
    class Solution {
        public int firstMissingPositive(int[] A) {
            for (int i = 0; i < A.length; i++) {
                if (A[i] < 1 || A[i] > A.length)
                    continue;
                int val = A[i];
                while (A[val-1] != val) {
                    int temp = A[val - 1];
                    A[val - 1] = val;
                    val = temp;
                    if (val < 1 || val > A.length)
                        break;
                }
            }
            for (int i = 0; i < A.length; i++) {
                if (A[i] != i+1) return i+1;
            }
            return A.length + 1;
        }
    }

    class Solution2 {
        public int firstMissingPositive(int[] A) {
            int len = A.length, i, index;
            for (i = 0; i < len; i++)
                if (A[i] < 0 || A[i] > len)
                    A[i] = 0;

            for (i = 0; i < len; i++) {
                if (A[i] != 0) {
                    index = (A[i] % len) - 1;
                    if (index == -1)
                        index = len - 1;
                    A[index] += len;
                }
            }

            for (i = 0; i < len; i++) {
                if (A[i] % len == 0)
                    return i+1;
            }

            return len + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new FindMissingPositive().new Solution2().firstMissingPositive(new int[] {2, 1})
        );
    }
}
