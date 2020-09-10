package code.dynamicprogramming;

import java.io.*;
import java.util.*;
import java.util.stream.*;
public class MaxSumSubsequenceNoAdjacentElements {

    public int maxSum(int arr[], int n) {
        int incl = arr[0];
        int excl = 0;
        int temp;

        for (int i = 1; i < n; i++) {
            temp = incl;
            incl = Math.max(incl, excl + arr[i]);
            excl = temp;
        }
        return ((incl > excl) ? incl : excl);
    }


    private static class CodeGladiators {

        public static void main(String args[]) throws Exception {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.valueOf(br.readLine());
            if (T == 0) return;
            int N;
            while (T-- > 0) {
                N = Integer.valueOf(br.readLine());
                if (N == 0) return;
                int[] tickets = new int[N];
                String[] line = br.readLine().split(" ");
                if (N < 2) System.out.println(line[0]);
                else {
                    IntStream.range(0, N).forEach(i -> tickets[i] = Integer.valueOf(line[i]));
                    maxSum(tickets).stream().sorted().forEach(System.out::print);
                    System.out.println();
                }
            }
        }

        private static List<Integer> maxSum(int a[]) {
            int incl, excl, temp;
            List<Integer> tempList = null;
            List<Integer> inclList = new ArrayList<>();
            List<Integer> exclList = new ArrayList<>();
            excl = a[0];
            exclList.add(a[0]);
            if (a[1] >= a[0]) {
                incl = a[1];
                inclList.add(a[1]);
            } else {
                incl = a[0];
                inclList.add(a[0]);
            }
            for (int i = 2; i < a.length; i++) {
                temp = incl;
                tempList = inclList;
                if (a[i] >= a[i] + excl && a[i] >= incl) {
                    incl = a[i];
                    inclList = new ArrayList<>();
                    inclList.add(a[i]);
                } else if (incl < excl + a[i]) {
                    incl = excl + a[i];
                    inclList = new ArrayList<>(exclList);
                    inclList.add(a[i]);
                }
                excl = temp;
                exclList = tempList;
            }
            if (incl > excl) return inclList;
            return exclList;
        }
    }
}
