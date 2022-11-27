package code.shubham.recursion;

import java.util.Scanner;

public class FindHeightOfTreeGivenParentArray {

    static int heighOfTreeFromParentArray(int parentArr[]) {
//        if(c < C-1 && r < R-1) {}
        return (Integer) null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.next());
        int n = Integer.parseInt(sc.next());
        int[] a = new int[n];
        for(int i=0;i<n;i++) {
            a[i] = Integer.parseInt(sc.next());
        }
        heighOfTreeFromParentArray(a);
    }
}
