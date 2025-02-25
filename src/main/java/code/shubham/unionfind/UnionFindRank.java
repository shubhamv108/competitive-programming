package code.shubham.unionfind;

public class UnionFindRank {
    int[] parent;
    int[] rank;
    int count;

    public UnionFindRank(int n) {
        count = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    int find(int a) {
        if (parent[a] != a)
            parent[a] = find(parent[a]);
        return parent[a];
    }

    void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (rank[x] > rank[y])
            parent[y] =x;
        else if (rank[y] > rank[x])
            parent[x] = y;
        else {
            parent[y] = x;
            ++rank[x];
            --count;
        }
    }
}
