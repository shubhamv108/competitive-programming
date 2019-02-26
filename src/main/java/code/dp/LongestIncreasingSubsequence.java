package code.dp;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    private static int[] a;
    private static int n;
    private static int x[];


    private static int longestLISLength() {
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)
                if (a[i] > a[j] && x[i] < x[j] + 1)
                    x[i] = x[j] + 1;
        return Arrays.stream(x).max().getAsInt();
    }



    public static int longestLISLength(int arr[]) {
        if(arr.length == 0) return 0;
        a = arr;
        n = a.length;
        x = new int[a.length];
        Arrays.fill(x, 1);
        return longestLISLength();
    }

    public static void main(String[] args) {
        int[] a = {10, 22, 9, 33, 21, 50, 41, 60};
        System.out.println(longestLISLength(a));
    }

}
