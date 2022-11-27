package code.shubham.commons.query.clauses.from.join;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJoin<X, Y> {
    private final List<Pair<X, Y>> joined = new ArrayList<>();
    protected final On on;

    protected AbstractJoin(List<X> x, List<Y> y, On on) throws IllegalAccessException {
        this.on = on;
        this.join(x, y);
    }

    abstract List<Pair<X, Y>> join(List<X> x, List<Y> y) throws IllegalAccessException;
}
