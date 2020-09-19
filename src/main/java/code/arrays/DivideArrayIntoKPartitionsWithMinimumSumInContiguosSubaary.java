package code.arrays;

import input.InputUtils;

import java.util.stream.IntStream;

public class DivideArrayIntoKPartitionsWithMinimumSumInContiguosSubaary {

    /** ToDo */
    int getMinimumSum(int[] arr) {
//
        int result = Integer.MAX_VALUE;
//        int sum1 = 0;
//        for (int i = 0; i < arr.length; i++) {
//            int sum2 = 0;
//            int sum3 = IntStream.range(i, arr.length).mapToObj(j -> arr[j]).mapToInt(j -> j).sum();
//            for (int j = i; j < arr.length; j++) {
//                sum2 += arr[j];
//                if (i < arr.length - 1) sum3 -= arr[j];
//                result = Math.min(result, Math.max(Math.max(sum1, sum2), sum3));
//
//            }
//            sum1 += arr[i];
//        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = InputUtils.nextIntLine();
        System.out.println(
                new DivideArrayIntoKPartitionsWithMinimumSumInContiguosSubaary().getMinimumSum(arr)
        );
    }

}
