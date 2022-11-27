package code.shubham.arrays;

import java.util.HashSet;

public class LargestKSuchThatKAndNegativeKExistInArray {

    int get(int[] arr) {
        int result = -1;
        HashSet<Integer> set = new HashSet<>();
        for (int n : arr) {
            if (set.contains(n*-1)) {
                result = Math.max(result, n);
            }
        }
        return Math.abs(result);
    }

}
