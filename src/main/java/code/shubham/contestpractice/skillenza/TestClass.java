package code.shubham.contestpractice.skillenza;//package code;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
////import for Scanner and other utility classes
//import java.util.*;
//
//// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
//
//class TestClass {
//
//    private static Map<Integer, List<Integer>> tree = new HashMap<>();
//    private static String s;
//    private static Map<Integer, Map<Character, Integer>> countCache = new HashMap<>();
//
//    public static void main(String args[] ) throws Exception {
//        String[] line = InputUtils.splitNextLine();
//        int n = Integer.valueOf(line[0]);
//        int q = Integer.valueOf(line[1]);
//        s = InputUtils.nextLine();
//        int m = n-1;
//        while(m-- > 0) {
//            line = InputUtils.splitNextLine();
//            int u = Integer.valueOf(line[0]);
//            int v = Integer.valueOf(line[1]);
//            List<Integer> children = tree.get(u);
//            if (Objects.isNull(children)) {
//                List<Integer> t = new ArrayList<>();
//                t.add(v);
//                tree.put(u, t);
//            } else {
//                children.add(v);
//            }
//        }
//        while (q-- > 0) {
//            int[] a = new int[q];
//            Arrays.stream(a).sum();
//            line = InputUtils.splitNextLine();
//            int u = Integer.valueOf(line[0]);
//            char c = line[1].charAt(0);
//            System.out.println(count(u, c));
//        }
//    }

import java.io.*;
import java.util.*;


public class TestClass {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().trim());
        for(int t_i=0; t_i<T; t_i++)
        {
            int N = Integer.parseInt(br.readLine().trim());
            String[] arr_A = br.readLine().split(" ");
            int[] A = new int[N];
            for(int i_A=0; i_A<arr_A.length; i_A++)
            {
                A[i_A] = Integer.parseInt(arr_A[i_A]);
            }

            int Q = Integer.parseInt(br.readLine().trim());
            String[] arr_L = br.readLine().split(" ");
            long[] L = new long[Q];
            for(int i_L=0; i_L<arr_L.length; i_L++)
            {
                L[i_L] = Long.parseLong(arr_L[i_L]);
            }

            String[] arr_R = br.readLine().split(" ");
            long[] R = new long[Q];
            for(int i_R=0; i_R<arr_R.length; i_R++)
            {
                R[i_R] = Long.parseLong(arr_R[i_R]);
            }

            long[] out_ = solve(A, R, L);
            System.out.print(out_[0]);
            for(int i_out_=1; i_out_<out_.length; i_out_++)
            {
                System.out.print(" " + out_[i_out_]);
            }
            System.out.println("");
        }

        wr.close();
        br.close();
    }
    static long[] solve(int[] A, long[] R, long[] L){
        long[] result = new long[R.length];
        long mod = 1000000007;
        long sumOfArray = Arrays.stream(A).sum() % mod;
        int length = A.length;
        for (int i = 0; i < R.length; i++) {

            int startPos =  ((Long)(L[i] % length)).intValue();
            long endPos   =  R[i] % length;
            long remainingNumbers = length - (length - startPos + 1) - endPos;
            result[i] = 0;
            for (int j = startPos - 1; j < length; j++) {
                result[i] = (result[i] + A[j]) % mod;
            }
            if (remainingNumbers > 1) {
                long countOfFullSizeArrays = remainingNumbers / length;
                for (int j = 1; j <= countOfFullSizeArrays; j++) {
                    result[i] = (result[i] + sumOfArray) % mod;
                }
            }
            for (int j = 0; j < endPos; j++) {
                result[i] = (result[i] + A[j]) % mod;
            }

        }
        return result;
    }
}
//
//    private static int count(int u, char c) {
//        int count = 0;
//        boolean requiresCalculation = true;
//        Map<Character, Integer> charCountMap = countCache.get(u);
//        if (Objects.nonNull(charCountMap)) {
//            Integer charCount = charCountMap.get(c);
//            if (Objects.nonNull(charCount)) {
//                count = charCount;
//                requiresCalculation = false;
//            }
//        } else {
//            charCountMap = new HashMap<>();
//            countCache.put(u, charCountMap);
//        }
//        if (requiresCalculation) {
//            List<Integer> children = tree.get(u);
//            if (s.charAt(u - 1) == c)
//                count++;
//            if (Objects.nonNull(children)) {
//                for (Integer child : children)
//                    count += count(child, c);
//            }
//            charCountMap.put(c, count);
//        }
//        return count;
//    }
//
//}
//
class InputUtils {

    private static BufferedReader BR;

    public static BufferedReader getBR() {
        if (null == BR) {
            BR = new BufferedReader(new InputStreamReader(System.in));
        }
        return BR;
    }

    public static String[] splitNextLine() {
        return splitNextLine(BR, " ");
    }

    public static String[] splitNextLine(BufferedReader br) {
        return splitNextLine(br, " ");
    }

    public static String[] splitNextLine(BufferedReader br, String regex) {
        return nextLine().split(regex);
    }

    public static String nextLine() {
        return nextLine(getBR());
    }

    public static String nextLine(BufferedReader br) {
        try {
            return br.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public static int toInteger(String s) {
        return Integer.valueOf(s);
    }

    public static long toLong(String s) {
        return Long.valueOf(s);
    }

    public static int nextInt() {
        return toInteger(nextLine());
    }

    public static long nextLong() {
        return toLong(nextLine());
    }

}
