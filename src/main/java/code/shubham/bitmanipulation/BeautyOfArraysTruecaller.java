package code.shubham.bitmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BeautyOfArraysTruecaller {
    class Solution {
        int solve(int[] A, int k, int y, int z) {
            int result = Integer.MIN_VALUE;
            ArrayList<int[]> subArrayANDs = this.getSubArrayANDs(A, k);
            Map<Integer, ArrayList<int[]>> dp = new HashMap<>();
            for (int i = 1; i <= k; i++)
                dp.put(i, new ArrayList<>());

            for (int[] a: subArrayANDs) {
                Arrays.stream(a).forEach(e -> System.out.print(e + ","));
                System.out.println();
            }

            for (int[] subArrayAND: subArrayANDs) {
                for (int i = k-1; i > 0; i--) {
                    ArrayList<int[]> l = dp.get(i);
                    for (int[] u: l) {
                        int[] newBitWiseAND = BitwiseAND(subArrayAND, u);
                        if (i == k-1) {
                            result = Math.max(result, z | getDecimal(newBitWiseAND));
                        } else {
                            dp.get(i + 1).add(newBitWiseAND);
                        }
                    }
                }
                dp.get(1).add(subArrayAND);
            }
            System.out.println(dp);
            return result;
        }

        ArrayList<int[]> getSubArrayANDs(int[] A, int k) {
            ArrayList<int[]> subArrayANDs = new ArrayList<>();
            Queue<int[]> q = new LinkedList<>();
            int[] bits = new int[32];
            for (int i = 0; i < A.length; i++) {
                if (q.size() == k) {
                    subArrayANDs.add(ANDBits(bits));
                    int[] p = q.poll();
                    removeFromA(bits, p);
                }
                int[] aBits = this.getBitArray(A[i]);
                addToA(bits, aBits);
                q.offer(aBits);
            }
            return subArrayANDs;
        }

        int[] ANDBits(int[] A) {
            int[] result = new int[32];
            for (int i = 0; i < 32; i++)
                if (A[i] > 1)
                    result[i] = 1;
            return result;
        }


        int[] BitwiseAND(int[] A, int[] B) {
            int[] result = new int[32];
            for (int i = 0; i < 32; i++)
                if (A[i] == 1 && B[i] == 1)
                    result[i] = 1;
            return result;
        }
        void addToA(int[] A, int[] B) {
            for (int i = 0; i < 32; i++)
                A[i] += B[i];
        }

        void removeFromA(int[] A, int[] B) {
            for (int i = 0; i < 32; i++)
                A[i] -= B[i];
        }

        int[] getBitArray(int a) {
            int[] result = new int[32];
            char[] chrs = Integer.toBinaryString(a).toCharArray();
            int rPos = 31;
            for (int i = chrs.length - 1; i > -1; i--)
                result[rPos--] = chrs[i] == '0' ? 0 : 1;
            return result;
        }

        Integer getDecimal(int[] a) {
            char[] chrs = new char[32];
            for (int i = 0; i < 32; i++)
                chrs[i] = a[i] > 0 ? '0' : '1';
            return Integer.parseInt(new String(chrs), 2);
        }

    }

    public static void main(String[] args) {
        System.out.println("dsadas " + (1 & 2));
        System.out.println("dsadas " + (2 & 3));
        System.out.println("dsadas " + (3 & 4));
        System.out.println();
        System.out.println();
        System.out.println();
        BeautyOfArraysTruecaller obj = new BeautyOfArraysTruecaller();
        Solution solution = obj.new Solution();
        int result = solution.solve(new int[] {1, 2, 3, 4}, 2, 2, 9);
        System.out.println(result);
    }
}
