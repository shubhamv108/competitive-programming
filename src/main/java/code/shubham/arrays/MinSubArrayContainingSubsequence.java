package code.shubham.arrays;

/**
 * Time Complexity: O(n^2)
 */
public class MinSubArrayContainingSubsequence {

    private int size(int[] A, int[] B) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < A.length - B.length + 1; i++) {
            if (A[i] == B[0]) {
                int k = 1;
                int subsequenceLength = 1;
                for (int j = i + 1; j < A.length; j++) {
                    subsequenceLength++;
                    if (A[j] == B[k]) {
                        k++;
                        if (k == B.length) {
                            result = Math.min(result, subsequenceLength);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
            new MinSubArrayContainingSubsequence().size(new int[] { 5, 6, 5, 2, 7, 5, 6, 7, 5, 5, 7 },
                                                         new int[] { 5, 5, 7 })
        );
    }

}
