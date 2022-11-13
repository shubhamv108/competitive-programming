package code.api.query.clauses.where;

import code.api.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class WhereClause implements Function<Data, Boolean> {
    private final List<Condition> conditions = new ArrayList<>();

    public void add(Condition condition) {
        this.conditions.add(condition);
    }

    public Boolean apply(Data data) {
        return conditions.stream().allMatch(condition -> {
            try {
                return condition.apply(data);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
