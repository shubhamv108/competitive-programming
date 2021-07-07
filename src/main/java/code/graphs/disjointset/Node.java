package code.graphs.disjointset;

public class Node<Data> {
    private Data data;
    private Node parent;
    public Node(Data data) {
        this.data = data;
        this.parent = this;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}