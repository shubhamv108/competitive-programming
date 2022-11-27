package code.shubham.contestpractice.hackerearth;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class SpecialNodes {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int N = Integer.parseInt(br.readLine().trim());
        String[] arr_val = br.readLine().split(" ");
        int[] val = new int[N];
        for(int i_val=0; i_val<arr_val.length; i_val++)
        {
            val[i_val] = Integer.parseInt(arr_val[i_val]);
        }
        String[] arr_parent = br.readLine().split(" ");
        int[] parent = new int[N-1];
        for(int i_parent=0; i_parent<arr_parent.length; i_parent++)
        {
            parent[i_parent] = Integer.parseInt(arr_parent[i_parent]);
        }

        int[] out_ = solve(parent, val);
        System.out.print(out_[0]);
        for(int i_out_=1; i_out_<out_.length; i_out_++)
        {
            System.out.print(" " + out_[i_out_]);
        }

        wr.close();
        br.close();
    }
    private static Map<Integer, Boolean> primeCache = new HashMap<>();
    private static Integer ALL_XOR = null;
    private static Map<Integer, List<Integer>> tree = new HashMap<>();
    private static int[] result = null;
    private static int[] val = null;
    static int[] solve(int[] parent, int[] val){
        result = new int[val.length];
        if (val != null) SpecialNodes.val = val;
        tree.put(1, new ArrayList<>());
        if (isPrime(val[0])) ALL_XOR = val[0];
        Integer xor = null;
        for (int i = 0; i < parent.length; i++) {
            List<Integer> children = tree.get(parent[i]);
            if (children == null) tree.put(parent[i], children = new ArrayList<>());
            children.add(i+2);
            if (isPrime(val[i+1])) {
                if (ALL_XOR == null) ALL_XOR = val[i+1];
                else ALL_XOR ^= val[i+1];
            }
        }
        if (null != ALL_XOR) {
            ALL_XOR = recurse(1);
        }
        return result;
    }

    private static int recurse(Integer n) {
        int nodeXOR = 0;
        if (n == null) return nodeXOR;
        int childXOR = 0;
        List<Integer> children = tree.get(n);
        if (children != null) for (int c: children) childXOR ^= recurse(c);
        if (isPrime(val[n-1])) nodeXOR = val[n-1];
        nodeXOR ^= childXOR;
        return result[n-1] = nodeXOR ^ ALL_XOR;
    }

    private static boolean isPrime(int n) {
        if (null == primeCache.get(n)) primeCache.put(n, new BigInteger(String.valueOf(n)).isProbablePrime(1));
        return primeCache.get(n);
    }
}