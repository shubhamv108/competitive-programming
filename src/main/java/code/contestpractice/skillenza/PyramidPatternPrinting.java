package code.contestpractice.skillenza;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class P {

   public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = i(br.readLine());
        String[] w;
        while (t-- > 0) {
            w = br.readLine().split(" ");
            char c[] = w[0].toCharArray();
            int l = i(w[1]), d = i(w[2]), p = 0, i, m, v, n = d == -1 ? l : 1, k;
            boolean r = true;
            while (n > 0 && n <= l) {
                k = 0;
                m = ((l-1)*2)+1;
                v = (((n-1)*2)+1);
                i = (m-v)/2;
                StringBuilder s = new StringBuilder(m);
                for (int j=0;j<m;j++) s.append(" ");
                while (k++ < v) s.setCharAt(i++, c[p++%c.length]);
                if (r = !r) s.reverse();
                System.out.println(s.toString());
                n += d;
            }
        }
    }

    static int i(String n) {
        return Integer.valueOf(n);
    }

}

