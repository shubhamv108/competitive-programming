package code.arrays;

public class PrettyPrint {

    /**
     * A = 4
     *
     * Output:
     * 4 4 4 4 4 4 4
     * 4 3 3 3 3 3 4
     * 4 3 2 2 2 3 4
     * 4 3 2 1 2 3 4
     * 4 3 2 2 2 3 4
     * 4 3 3 3 3 3 4
     * 4 4 4 4 4 4 4
     *
     *
     * @param A
     * @return
     */
    public static java.util.ArrayList<java.util.ArrayList<Integer>> prettyPrint(int A) {
        java.util.ArrayList<java.util.ArrayList<Integer>> a = new java.util.ArrayList();
        int k = A;
        A = (2*A)-1;
        for (int i=0;i<A;i++) {
            java.util.ArrayList<Integer> aa = new java.util.ArrayList<>();
            for (int j=0;j<A;j++) {
                aa.add(0);
            }
            a.add(aa);
        }
        int R = A - 1;
        int C = A - 1;
        int r = 0;
        int c = 0;

        while (c <= C && r <= R) {
            for (int i=c;i<=C;i++)     a.get(r).set(i, k);
            r++;
            for (int i=r;i<=R;i++)     a.get(i).set(C, k);
            C--;
            if (r<=R) {
                for (int i=C;i>=c;i--) a.get(R).set(i, k);
                R--;
            }
            if (c<=C) {
                for (int i=R;i>=r;i--) a.get(i).set(c, k);
                c++;
            }
            k--;
        }
        return a;
    }

}
