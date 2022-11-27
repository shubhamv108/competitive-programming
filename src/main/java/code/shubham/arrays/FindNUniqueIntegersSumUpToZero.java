package code.shubham.arrays;

public class FindNUniqueIntegersSumUpToZero {

    int[] sumZero(int n) {
        int[] result = new int[n];
        for (int i = 0; i < n; ++i)
            result[i] = i * 2 - n + 1;
        return result;
    }

}
