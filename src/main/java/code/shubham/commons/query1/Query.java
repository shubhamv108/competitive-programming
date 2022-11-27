package code.shubham.commons.query1;

import code.shubham.commons.query.clauses.from.FromClause;
import code.shubham.commons.query.clauses.select.SelectClause;
import code.shubham.commons.query.clauses.where.WhereClause;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Query<Data, Response> implements Function<Data, Response> {

    private final SelectClause<Data, Response> selectClause;

    private final FromClause<Data> fromClause;
    private final WhereClause<Data> whereClause;

    public Query(
            SelectClause<Data, Response> selectClause,
            FromClause<Data> fromClause,
            WhereClause whereClause) {
        this.selectClause = selectClause;
        this.fromClause = fromClause;
        this.whereClause = whereClause;
    }

    public List<Response> apply() {
        List<Response> result = new ArrayList<>();
        for (Data data: fromClause.getFrom()) {
            Response response = this.apply(data);
            if (response != null)
                result.add(response);
        }
        return result;
    }

    public Response apply(Data data) {
        if (whereClause.apply(data))
            return this.selectClause.apply(data);
        return null;
    }

    @Override
    public String toString() {
        return "Query{" +
                "whereClause=" + whereClause +
                '}';
    }
}