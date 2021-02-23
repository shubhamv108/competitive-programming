package code.graphs.disjointset;

public class DisjointSetUnionByRankAndPathCompression {

    private static class Node {
        int rank;
        int data;
        Node parent;
        Node(int data) {
            this.rank = 1;
            this.data = data;
            this.parent = this;
        }
    }

    void makeSet() {

    }

    void union(int x, int y) {

    }

    void findSet(int x) {

    }

    public static void main(String[] args) {
        Node one    = new Node(1);
        Node two    = new Node(2);
        Node three  = new Node(3);
        Node four   = new Node(4);
        Node five   = new Node(5);
        Node six    = new Node(6);
        Node seven  = new Node(7);
    }

}
