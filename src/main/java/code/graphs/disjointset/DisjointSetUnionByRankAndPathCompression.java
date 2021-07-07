package code.graphs.disjointset;

import java.util.HashMap;
import java.util.Map;

public class DisjointSetUnionByRankAndPathCompression {

    private Map<Long, RankSetNode> map = new HashMap<>();

    IUnionStrategy<Node> unionStrategy;
    IUnionFactory<Node>  unionFactory;

    DisjointSetUnionByRankAndPathCompression(IUnionStrategy<Node> unionStrategy, IUnionFactory<Node> unionFactory) {
        this.unionStrategy = unionStrategy;
        this.unionFactory  = new UnionFactory(unionStrategy);
    }

    void makeSet(Long data) {
        this.map.put(data, new RankSetNode(data));
    }

    void union(Long x, Long y) {
        RankSetNode parentX = (RankSetNode) this.findSet(this.map.get(x));
        RankSetNode parentY = (RankSetNode) this.findSet(this.map.get(y));

        if (parentX.getRank() >= parentY.getRank()) {
            
            parentY.setParent(parentX);
        } else {
            parentX.setParent(parentY);
        }

    }

    Node findSet(Node node) {
        Node setNode = node;
        while (setNode.getParent() != setNode)
            setNode = setNode.getParent();
        if (node.getParent() != setNode) node.setParent(setNode);
        return node;
    }

    private static class RankSetNode extends Node<Long> {
        private int rank;
        RankSetNode(Long data) {
            super(data);
            this.rank = 0;
        }

        public int getRank() {
            return this.rank;
        }
    }

    private class RankUnionStrategy implements IUnionStrategy<RankSetNode> {
        @Override
        public ParentChildRelation<RankSetNode> findRelation(RankSetNode x, RankSetNode y) {
            return x.rank < y.rank ? new ParentChildRelation<>(y, x) : new ParentChildRelation<>(x, y);
        }
    }

    public static void main(String[] args) {
        Node one    = new RankSetNode(1L);
        Node two    = new RankSetNode(2L);
        Node three  = new RankSetNode(3L);
        Node four   = new RankSetNode(4L);
        Node five   = new RankSetNode(5L);
        Node six    = new RankSetNode(6L);
        Node seven  = new RankSetNode(7L);
    }

}
