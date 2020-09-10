package code.dynamicprogramming;

import java.util.Arrays;

public class SubsetSum {

    static int timeComplexityCounter;
    static boolean[][] cache;
    static int[][] cache2;

    static boolean isSubsetSum(int[] arr, int sum, int n, int ai) {
        timeComplexityCounter++;
        if (sum == 0)      return true;
        if (ai == n)       return false;
        if (arr[ai] > sum) return isSubsetSum(arr, sum, n, ai+1);
        return isSubsetSum(arr, sum - arr[ai], n,ai+1) || isSubsetSum(arr, sum, n, ai+1);
    }

    static int isSubsetSum2(int[] arr, int sum, int n, int ai) {
        timeComplexityCounter++;
        if (sum == 0)              return cache2[sum][ai] = 1;
        if (ai == n)               return 0;
        if (cache2[sum][ai] != -1) return cache2[sum][ai];
        if (arr[ai] > sum)         return cache2[sum][ai] = isSubsetSum2(arr, sum, n, ai+1);
        if (isSubsetSum2(arr,sum - arr[ai], n,ai+1) == 1 || isSubsetSum2(arr, sum, n, ai+1) == 1) return cache2[sum][ai] = 1;
        else return cache2[sum][ai] = 0;
    }

    static boolean isSubsetSum3(int[] arr, int sum, int n) {
        timeComplexityCounter++;
        if (sum == 0)       return true;
        if (n == 0)         return false;
        if (arr[n-1] > sum) return isSubsetSum(arr, n, sum, n-1);
        return isSubsetSum3(arr, sum, n-1) || isSubsetSum3(arr, sum - arr[n-1], n-1);
    }

    static int isSubsetSum4(int[] arr, int sum, int n) {
        timeComplexityCounter++;
        if (sum == 0)               return cache2[sum][n] = 1;
        if (n == 0)                 return 0;
        if (cache2[sum][n] != -1)   return cache2[sum][n];
        if (arr[n-1] > sum)         return cache2[sum][n] = isSubsetSum4(arr, sum, n-1);
        if (isSubsetSum4(arr, sum, n-1) == 1 || isSubsetSum4(arr, sum - arr[n-1], n-1) == 1) return cache2[sum][n] = 1;
        else return cache2[sum][n] = 0;
    }

    static boolean isSubsetSum5(int[] arr, int sum, int n) {
        for (int i = 0; i <= n; i++)
            cache[0][i] = true;
        for (int i = 1; i <= sum; i++)
            cache[i][0] = false;
        for (int i=1; i <= sum; i++) {
            for (int j=1; j <= n; j++) {
                cache[i][j] = cache[i][j-1];
                timeComplexityCounter++;
                if (i >= arr[j-1])
                    cache[i][j] =
                            cache[i][j] ||
                            cache[i - arr[j-1]][j-1];
            }
        }
        return cache[sum][n];
    }

    public static void main(String[] args) {
        timeComplexityCounter = 0;
        int n = 10;
        int[] arr = {3, 34, 4, 12, 5, 2, 4, 6, 7, 8};
        int sum = 25;
        cache2 = new int[sum+1][n+1];
        cache = new boolean[sum+1][n+1];
        int i = 0;
        while(i++ < sum) Arrays.fill(cache2[i], -1);
        System.out.println(isSubsetSum5(arr, sum, n));
        System.out.println(timeComplexityCounter);

//        cache2 = new int[sum+1][n+1];
//        int i =0;
//        while(i++ < sum) Arrays.fill(cache2[i], -1);
//        System.out.println(isSubsetSum2(arr, n, sum, 0) == 1 ? true : false);
//        System.out.println(timeComplexityCounter);
//        for(i = 0;i < sum+1; i++) {
//            for(int j = 0;j < n+1; j++) System.out.print(cache2[i][j] + " ");
//            System.out.println();
//        }
    }
}
