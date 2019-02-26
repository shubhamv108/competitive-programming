package code.arrays;

import java.util.Scanner;

public class Array {

    private Object[] a;
    private int n;

    Array(int n) { this.a = new Object[n]; this.n = n; }

    public void set (int idx, Object value) { a[idx] = value; }
    public Object get (int idx)             { return a[idx]; }

    void rightRotate() {
        Object temp;
        temp = a[n - 1];
        int i;
        for (i = n - 1; i > 0; i--) a[i] = a[i - 1];
        a[i] = temp;
    }

}

class RightRotate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            Array arr =  new Array(n);
            for(int i=0;i<n;i++) { arr.set(i, sc.nextInt()); System.out.printf("%d ", arr.get(i)); }
            int k = sc.nextInt();
            while(k-- > 0) { arr.rightRotate(); }
            System.out.printf("\n");
            for(int i=0;i<n;i++) { System.out.printf("%d ", arr.get(i)); }
            System.out.printf("\n\n");
        }
    }
}