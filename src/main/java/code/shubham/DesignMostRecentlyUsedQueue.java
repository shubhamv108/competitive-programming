package code.shubham;

public class DesignMostRecentlyUsedQueue {
    class MRUQueue {
        int N;
        int[] q;

        public MRUQueue(int n) {
            N = n;
            q = new int[n];
            for (int i = 0; i < n; ++i)
                q[i] = i + 1;
        }

        public int fetch(int k) {
            if (k == N)
                return q[N - 1];

            int val = q[k - 1];
            System.arraycopy(q, k, q, k - 1, N - k);
            q[N - 1] = val;
            return val;
        }
    }

    void main(String[] args) {
        System.out.println(new MRUQueue(2));
    }
}
