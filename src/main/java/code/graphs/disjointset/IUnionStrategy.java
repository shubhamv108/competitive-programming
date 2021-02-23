package code.graphs.disjointset;

public interface UnionStrategy<Node> {

    Node getParent(Node x, Node y);
    Node getChild(Node x, Node y);

}
