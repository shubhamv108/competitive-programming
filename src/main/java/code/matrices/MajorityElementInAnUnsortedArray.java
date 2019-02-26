package code.matrices;

import java.util.Scanner;

class MajorityElementInAnUnsortedArray {

    static int findCandidate(int[] a) {
        int c = 0, m = 0;
        for(int i=0; i<=a.length; i++) {
            if(a[m] == a[i]) {
                c++;
            } else {
                c--;
                if (c == -1) {
                    m = i;
                }
            }
        }
        return m;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.next());
        while(t-- > 0) {
            int n = Integer.parseInt(sc.next());
            int[] a = new int[n];
            for(int i=0; i < n; i++) {
                a[i] = Integer.parseInt(sc.next());
            }

        }
    }
}
