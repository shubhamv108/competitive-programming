package code.graphs;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DecideAlphabetOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        String[] arr;
        while (t-- > 0) {
            int n = sc.nextInt();
            arr = new String[n];
            for (int i=0;i<n;i++) arr[i] = sc.next();
            Set<Character> visited = new HashSet<>();
            for (int i=0;i<n;i++) {
                for (int j=0; i<arr[i].length(); i++) {
                    if(j == 0) visited.add(arr[i].charAt(0));
//                    if(arr[i] == ) {
//
//                    }
                }
            }

        }

    }
}
