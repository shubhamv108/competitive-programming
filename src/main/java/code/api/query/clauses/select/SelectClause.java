package code.api.query.clauses.select;

import code.api.Data;
import code.api.query.result.AbstractQueryResult;

import java.util.function.Function;

public class SelectClause implements Function<Data, AbstractQueryResult> {

    private Function<Data, AbstractQueryResult> selector;

    SelectClause(Function<Data, AbstractQueryResult> selector) {
        this.selector = selector;
    }

    public AbstractQueryResult apply(Data data) {
        return this.selector.apply(data);
    }

}
