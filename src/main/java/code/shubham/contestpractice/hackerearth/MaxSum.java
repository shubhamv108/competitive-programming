package code.shubham.contestpractice.hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaxSum {

    private static int[] arr;
    static void printMaxSumOfUniqueElementsInSubArray()
    {
        int max = Integer.MIN_VALUE, curMax= 0, start = 0, end = 0, s = 0;
        for (int i = 0; i < arr.length; i++) {
            curMax += arr[i];
            if (max < curMax) {
                max = curMax;
                start = s;
                end = i;
            }
            if (curMax < 0) {
                curMax = 0;
                s = i + 1;
            }
        }
        int UNIQUE = 0, sum = 0;
        for(int i=start; i<=end; i++) {
            if((UNIQUE & (1 << i)) == 0) {
                sum += arr[i];
                UNIQUE = (UNIQUE | (1 << i));
                if(UNIQUE == 1023) break;
            }
        }
        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int n = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        arr = new int[n];
        for(int i=0;i<n;i++) arr[i] = Integer.parseInt(line[i]);
        printMaxSumOfUniqueElementsInSubArray();
    }
}
