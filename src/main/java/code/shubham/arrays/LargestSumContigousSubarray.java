package code.shubham.arrays;

import java.util.Arrays;

public class  LargestSumContigousSubarray {

    public long get(long[] arr) {
        long sum        = arr[0];
        long largestSum = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            sum        = Math.max(arr[i], sum + arr[i]);
            largestSum = Math.max(largestSum, sum);
        }
        return largestSum;
    }

    long[] getLargestContiguosSubArray(long[] arr) {
        long sum        = arr[0];
        long largestSum = arr[0];
        int curStart = 0, curEnd = 0, resultStart = 0, resultEnd = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > sum + arr[i]) {
                sum = 0;
                curStart = curEnd = i;
            } else {
                curEnd++;
            }
            sum += arr[i];
            if (sum > largestSum) {
                resultStart = curStart;
                resultEnd = curEnd;
                largestSum = sum;
            }
        }

        if (largestSum > 0)
            for (int idx = resultStart; arr[idx] == 0 && idx <= resultEnd; idx++)
                resultStart++;

        long[] result = new long[resultEnd - resultStart + 1];
        for (int i = 0, idx = resultStart; idx<= resultEnd; idx++, i++)
            result[i] = arr[idx];
        return result;
    }

    public static void main(String[] args) {
        Arrays.stream(
                new LargestSumContigousSubarray().getLargestContiguosSubArray(new long[] {8, -7, -3, 5, 6, -2, 3, -4, 2}))
                .forEach(e -> System.out.print(e + " "));
    }

}
