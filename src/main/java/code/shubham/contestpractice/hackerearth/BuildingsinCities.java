package code.shubham.contestpractice.hackerearth;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BuildingsinCities {
    static class TestClass {
        public static void main(String args[] ) throws Exception {
            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt();
            long arr[] = new long[N];
            new Thread();
            for(int i=0; i<N; i++)
                arr[i] = sc.nextLong();
            long out = solve(arr, N);
            System.out.println(out);
        }
        public static long solve(long[] arr, long N){
            long result = 0;
            Map<Long, Long> m = new HashMap<>();
            for (int i = 0; i < N; i++) {
                if (arr[i] == 1)
                    continue;
                Long c = m.get(arr[i]);
                if (c == null)
                    c = 1l;
                m.put(arr[i], c);

                c = m.get(Math.pow(arr[i], 2));
                if (c != null)
                    result += c;

                Long sq = ((Double) Math.floor(Math.sqrt(arr[i]))).longValue();
                if ( (sq * sq) == arr[i] ) {
                    c = m.get(sq);
                    if (c != null)
                        result += c;
                }

            }

            return result;
        }
    }

}

