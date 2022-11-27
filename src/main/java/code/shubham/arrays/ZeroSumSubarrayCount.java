package code.shubham.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ZeroSumSubarrayCount {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());
        while (t-- > 0) {
            int n = Integer.valueOf(br.readLine());
            Integer[] A = Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).toArray(Integer[]::new);
            int count = 0, sum = 0;
            HashMap<Integer, Integer> sums = new HashMap();
            sums.put(0, 1);
            for (int i = 0; i < A.length; i++) {
                sum += A[i];
                Integer c = sums.get(sum);
                if (c != null) count += c;
                sums.merge(sum, 1, Integer::sum);
            }
            System.out.println(count);
        }
    }
}
