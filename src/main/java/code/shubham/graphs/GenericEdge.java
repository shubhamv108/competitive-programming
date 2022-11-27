package code.shubham.graphs;

import java.util.Comparator;
import java.util.Objects;

public class GenericEdge<V, C> implements Comparable<C> {

    public V v;
    public C c;

    public Comparator<C> comparator = null;

    public GenericEdge(V v, C c) {
        this.v = v;
        this.c = c;
    }

    public void setComparator(Comparator<C> c) {
        this.comparator = c;
    }

    @Override
    public int compareTo(C c){
        return Objects.compare(this.c, c, comparator);
    }

    @Override
    public String toString() { return "" + v + "(" + c.toString() + ") "; }

}
