package code.shubham.unionfind;

public class UnionFind {
    int[] p, size;
    int count;

    public UnionFind(int n) {
        count = n;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    int find(int a) {
        if (p[a] != a)
            p[a] = find(p[a]);
        return p[a];
    }

    void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            p[y] = x;
            --count;
        }
    }

    void unionBySize (int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y)
            return;

        if (size[x] > size[y]) {
            int t = size[x];
            size[x] = size[y];
            size[y] = t;
        }

        p[x] = y;
        size[y] += size[x];
    }
}
