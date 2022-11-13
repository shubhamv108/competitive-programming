package code.api.query;

import code.api.Data;
import code.api.query.clauses.select.SelectClause;
import code.api.query.clauses.where.WhereClause;
import code.api.query.result.AbstractQueryResult;
import code.api.query.result.concrete.NullQueryResult;

import java.util.function.Function;

public class Query implements Function<Data, AbstractQueryResult> {

    private final SelectClause selectClause;
    private final WhereClause whereClause;

    Query(SelectClause selectClause,
          WhereClause whereClause) {
        this.selectClause = selectClause;
        this.whereClause = whereClause;
    }

    public AbstractQueryResult apply(Data data) {
        if (whereClause.apply(data))
            return this.selectClause.apply(data);
        return new NullQueryResult();
    }

    @Override
    public String toString() {
        return "Query{" +
                "whereClause=" + whereClause +
                '}';
    }
}