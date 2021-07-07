package code.graphs.disjointset;

public interface IUnionFactory<Node> {

    Node getUnion(Node x, Node y);

}
