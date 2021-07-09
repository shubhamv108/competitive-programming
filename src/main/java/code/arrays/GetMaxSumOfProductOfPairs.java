package code.arrays;

import java.util.Arrays;

public class GetMaxSumOfProductOfPairs {

    int getSum(int[] arr) {
        int result = 0;
        Arrays.sort(arr);
        result = getPositiveSum(arr) + getNegativeSum(arr);
        return result;
    }

    int getPositiveSum(int[] arr) {
        int sum = 0;
        int i = arr.length - 1;
        while (i > -1 && arr[i] > 0) {
            if (i > 0 && arr[i-1] > 0) {
                sum += (arr[i] * arr[i - 1]);
                i-=2;
            } else {
                sum += arr[i--];
            }
        }
        return sum;
    }

    int getNegativeSum(int[] arr) {
        int sum = 0;
        int i = 0;
        while (i < arr.length && arr[i] < 0) {
            if (i < arr.length - 1 && arr[i + 1] < 1) {
                sum += (arr[i] * arr[i + 1]);
                i+=2;
            } else {
                sum += arr[i++];
            }
        }
        return sum;
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        System.out.println(new GetMaxSumOfProductOfPairs().getSum(new int[] { 3, 6, 7, -2, -1, 0, 0 }));
    }

}
