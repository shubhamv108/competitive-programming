package code.arrays;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MaxTrips {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        if (n <= 0) return;
        float[] arr = new float[100];
        for (int i=0;i<n;i++) arr[i] = Float.valueOf(br.readLine());
        System.out.println(efficientJanitor(arr));
    }

    private static int efficientJanitor(float[] arr) {
        Arrays.sort(arr);
        int lastPos = arr.length;
        float remainning = (float) 3.0 - arr[lastPos];
        // float g = binarySearch();
        return 0;
    }

}
