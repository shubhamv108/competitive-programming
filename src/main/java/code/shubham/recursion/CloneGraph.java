package code.shubham.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CloneGraph {

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    class Solution {
        private final Map<Node, Node> visited = new HashMap<>();
        public Node cloneGraph(Node node) {
            if (node == null)
                return null;

            Node copy = visited.get(node);
            if (copy != null)
                return copy;

            ArrayList<Node> neighbors = node.neighbors
                    .stream()
                    .map(this::cloneGraph)
                    .collect(Collectors.toCollection(ArrayList::new));
            copy = new Node(node.val, neighbors);

            visited.put(node, copy);
            return copy;
        }
    }
}
