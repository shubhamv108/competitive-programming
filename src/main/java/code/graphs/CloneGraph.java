package code.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CloneGraph {

    class Solution {
        public UndirectedGraphNode cloneGraph (UndirectedGraphNode node) {
            Queue<UndirectedGraphNode> q = new LinkedList<>();
            q.add(node);
            HashMap<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<>();
            hm.put(node,new UndirectedGraphNode(node.label));
            while (!q.isEmpty()) {
                UndirectedGraphNode u = q.poll();
                UndirectedGraphNode cloneNodeU = hm.get(u);
                if (u.neighbors != null) {
                    List<UndirectedGraphNode> v = u.neighbors;
                    for (UndirectedGraphNode graphNode : v) {
                        UndirectedGraphNode cloneNodeG = hm.get(graphNode);
                        if (cloneNodeG == null) {
                            q.add(graphNode);
                            cloneNodeG = new UndirectedGraphNode(graphNode.label);
                            hm.put(graphNode, cloneNodeG);
                        }
                        cloneNodeU.neighbors.add(cloneNodeG);
                    }
                }
            }
            return hm.get(node);
        }
    }

    private class Solution2Recursive {
        HashMap<UndirectedGraphNode, UndirectedGraphNode> visited = new HashMap<>();
        public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
            UndirectedGraphNode n = visited.get(node);
            if (n != null) return n;
            UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
            visited.put(node, newNode);
            for (UndirectedGraphNode neighbor : node.neighbors) {
                newNode.neighbors.add(cloneGraph(neighbor));
            }
            return newNode;
        }
    }

    public void bfs(UndirectedGraphNode source) {
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        q.add(source);
        HashSet<UndirectedGraphNode> visit = new HashSet<>();
        visit.add(source);
        while (!q.isEmpty()) {
            UndirectedGraphNode u = q.poll();
            System.out.println("Value of Node " + u.label);
            System.out.println("Address of Node " + u);
            if (u.neighbors != null) {
                List<UndirectedGraphNode> v = u.neighbors;
                for (UndirectedGraphNode g : v) {
                    if (!visit.contains(g)) {
                        q.add(g);
                        visit.add(g);
                    }
                }
            }
        }
        System.out.println();
    }

    public UndirectedGraphNode buildGraph() {
        UndirectedGraphNode node1   = new UndirectedGraphNode(1);
        UndirectedGraphNode node2   = new UndirectedGraphNode(2);
        UndirectedGraphNode node3   = new UndirectedGraphNode(3);
        UndirectedGraphNode node4   = new UndirectedGraphNode(4);
        List<UndirectedGraphNode> v = new ArrayList<>();
        v.add(node2);
        v.add(node4);
        node1.neighbors = v;
        v = new ArrayList<>();
        v.add(node1);
        v.add(node3);
        node2.neighbors = v;
        v = new ArrayList<>();
        v.add(node2);
        v.add(node4);
        node3.neighbors = v;
        v = new ArrayList<>();
        v.add(node3);
        v.add(node1);
        node4.neighbors = v;
        return node1;
    }

    public static void main(String[] args) {
        CloneGraph graph = new CloneGraph();
        Solution solution = graph.new Solution();
        UndirectedGraphNode source = graph.buildGraph();
        System.out.println("BFS traversal of a graph before cloning");
        graph.bfs(source);
        UndirectedGraphNode newSource = solution.cloneGraph(source);
        System.out.println("BFS traversal of a graph after cloning");
        graph.bfs(newSource);
    }

}
