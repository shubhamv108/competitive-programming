package code.shubham.commons.query.clauses.from.join;

import code.shubham.commons.utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InnerJoin<X, Y> extends AbstractJoin<X, Y> {
    public InnerJoin(List<X> x, List<Y> y, On on) throws IllegalAccessException {
        super(x, y, on);
    }

    protected List<Pair<X, Y>> join(List<X> X, List<Y> Y) throws IllegalAccessException {
        List<Pair<X, Y>> joined = new ArrayList<>();
        Map<Object, List<Integer>> xValueIndexes = new HashMap<>();
        for (int xIndex = 0; xIndex < X.size(); xIndex++) {
            Object val = ReflectionUtils.getValueForField(this.on.getxField(), 0, X.get(xIndex));
            List<Integer> indexes = xValueIndexes.get(val);
            if (indexes == null)
                xValueIndexes.put(val, indexes = new ArrayList<>());
            indexes.add(xIndex);
        }

        for (Y y: Y) {
            Object val = ReflectionUtils.getValueForField(this.on.getxField(), 0, y);
            List<Integer> xJoinIndexes = xValueIndexes.get(val);
            Optional.ofNullable(xJoinIndexes).
                    ifPresent(xIndexList ->
                            xIndexList.forEach(xIndex ->
                                    joined.add(new Pair<>(X.get(xIndex), y))));
        }
        return joined;
    }
}
