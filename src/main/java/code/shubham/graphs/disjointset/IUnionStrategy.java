package code.shubham.graphs.disjointset;

public interface IUnionStrategy<Node> {

    ParentChildRelation<Node> findRelation(Node x, Node y);

}

class ParentChildRelation<Node> {
    private Node parent;
    private Node child;
    public ParentChildRelation(Node parent, Node child) {
        this.parent = parent;
        this.child  = child;
    }

    public Node getParent() {
        return this.parent;
    }

    public Node getChild() {
        return this.child;
    }
}
