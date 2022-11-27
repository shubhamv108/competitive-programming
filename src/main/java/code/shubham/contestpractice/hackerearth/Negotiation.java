package code.shubham.contestpractice.hackerearth;

import java.io.*;
import java.util.*;
import java.util.stream.*;


public class Negotiation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        String[] temp=br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int X = Integer.parseInt(temp[2]);
        int[] a = new int[N];
        int[] b = new int[N];
        for(int i_b=0; i_b<N; i_b++)
        {
            String[] a_b = br.readLine().split(" ");
            a[i_b] = Integer.parseInt(a_b[0]);
            b[i_b] = Integer.parseInt(a_b[1]);
        }
        String[] arr_c = br.readLine().split(" ");
        int[] c = new int[M];
        for(int i_c=0; i_c<arr_c.length; i_c++)
        {
            c[i_c] = Integer.parseInt(arr_c[i_c]);
        }

        int out_ = solve(a, c, b, M, N, X);
        System.out.println(out_);

        wr.close();
        br.close();
    }
    static int solve(int[] a, int[] c, int[] b, int M, int N, int X){
        int returnedResult = 0;
        List<Integer> arr = new ArrayList<>();
        IntStream.range(0, N).forEach(i -> arr.add(b[i] - a[i]));
        Arrays.stream(c).forEach(e -> arr.add(e));
        Collections.sort(arr);
        Long sum = 0L;
        for (Integer e: arr) {
            sum += e;
            if (sum > X) break;
            returnedResult++;
        }
        return returnedResult;
    }
}
