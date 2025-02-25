package code.shubham.unionfind;

public class UnionFind {
    int[] parent;
    int count;

    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; ++i)
            parent[i] = i;
    }

    int find(int a) {
        if (parent[a] != a)
            parent[a] = find(parent[a]);
        return parent[a];
    }

    void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
            --count;
        }
    }
}
