package code.shubham.unionfind;

public class UnionFindRank {
    int[] p;
    int[] rank;
    int count;

    public UnionFindRank(int n) {
        count = n;
        p = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            rank[i] = 0;
        }
    }

    int find (int x) {
        if (p[x] != x)
            p[x] = find(p[x]);
        return p[x];
    }

    void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (rank[x] > rank[y])
            p[y] = x;
        else if (rank[y] > rank[x])
            p[x] = y;
        else {
            p[y] = x;
            ++rank[x];
            --count;
        }
    }
}
