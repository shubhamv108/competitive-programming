package code.arrays;

/**
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array.
 * When you are at index i, you can jump to i + arr[i] or i - arr[i],
 * check if you can reach to any index with value 0.
 */
public class JumpGameIII {

    public boolean canReach(int[] arr, int start) {
        return  start >= 0
                &&
                start < arr.length
                &&
                arr[start] >= 0
                &&
                (
                        (arr[start] = -arr[start]) == 0
                        ||
                        canReach(arr, start + arr[start])
                        ||
                        canReach(arr, start - arr[start])
                );
    }

}
