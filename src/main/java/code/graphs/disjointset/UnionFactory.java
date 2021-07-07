package code.graphs.disjointset;

public class UnionFactory implements IUnionFactory<Node> {
    private IUnionStrategy<Node> unionStrategy;
    UnionFactory(IUnionStrategy<Node> unionStrategy) { this.unionStrategy = unionStrategy; }
    @Override
    public Node getUnion(Node x, Node y) {
        ParentChildRelation<Node> relation = this.unionStrategy.findRelation(x, y);
        relation.getChild().setParent(relation.getParent());
        return relation.getParent();
    }
}