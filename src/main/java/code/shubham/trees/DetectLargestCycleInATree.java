package code.shubham.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DetectLargestCycleInATree {
    static int ans;
    static int diameterHelper(int v) {
        if(tree.get(v).size() == 0) return 1;
        int esum = 0;
        int maxChildHeight = 0;
        int height;
        for(int e : tree.get(v)) {
            height = diameterHelper(e);
            esum += height;
            maxChildHeight = Math.max(height, maxChildHeight);
        }
        ans = Math.max(ans, 1 + esum);
        return 1 + maxChildHeight;
    }

    static int diameter() {
        ans = -1;
        diameterHelper(root);
        return ans;
    }

    static boolean[] visited;
    public static void insert(int v, int e) {
        if(tree.containsKey(v)) tree.get(v).add(e);
        else {
            List<Integer> l = new ArrayList<>();
            l.add(e);
            tree.put(v, l);
        }
    }
    static int[] dist;
    static int findFarthestNode(int v) {
        dist = new int[tree.size()+1];
        dist[v] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        while(!q.isEmpty()) {
            v = q.poll();
            for (int e : tree.get(v)) {
                if(dist[e] == 0) { q.offer(e); dist[e] = dist[v] + 1; }
            }
        }
        int max = Integer.MIN_VALUE;
        int maxNode = root;
        for(int i=1;i<=tree.size();i++) if(dist[i] > max) { max = dist[i]; maxNode = i; }
        return maxNode;
    }
    private static HashMap<Integer, List<Integer>> tree = new HashMap<>();
    static int root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        int E = n-1;
        String[] line;
        while (E-- > 0) {
            line = br.readLine().split(" ");
            int u = Integer.parseInt(line[0]);
            if(E == n-2) root = u;
            int v = Integer.parseInt(line[1]);
            insert (u, v);
            insert (v, u);
        }
        int farthestNodeFromRoot = findFarthestNode(root);
        int[] path = new int[n];
        System.out.println(farthestNodeFromRoot + " " + findFarthestNodeAndGetFullPath(farthestNodeFromRoot, path));

    }

    private static int findFarthestNodeAndGetFullPath(int v, int[] path) {
        return 0;
    }
}
