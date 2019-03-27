package code.arrays;

public class  LargestSumContigousSubarray {

    public long get(long[] arr) {
        long sum = arr[0];
        long largestSum = arr[0];
        for (int i = 1;i<arr.length;i++) {
            sum = Math.max(arr[i], sum+arr[i]);
            largestSum = Math.max(largestSum, sum);
        }
        return largestSum;
    }

}
