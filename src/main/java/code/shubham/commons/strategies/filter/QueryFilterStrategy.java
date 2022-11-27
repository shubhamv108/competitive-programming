package code.shubham.commons.strategies.filter;

import code.shubham.commons.query.clauses.from.FromClause;
import code.shubham.commons.query.clauses.select.SelectClause;
import code.shubham.commons.query.clauses.where.Filter;
import code.shubham.commons.query.clauses.where.WhereClause;
import code.shubham.commons.query1.Query;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class QueryFilterStrategy<Data> implements FilterStrategy<Data> {

    private final Collection<Data> datas = new HashSet<>();

    @Override
    public void add(Data data) {
        this.datas.add(data);
    }

    @Override
    public void add(String documentId, Data data) {}

    @Override
    public Collection<Data> filter(Map<String, Object> filterCondition) {
        SelectClause<Data, Data> selectClause = new SelectClause((issue) -> issue);
        WhereClause<Data> whereClause = new WhereClause<>();
        whereClause.add(new Filter<>(filterCondition));
        FromClause<Data> fromClause = new FromClause<>(datas);
        Query<Data, Data> query = new Query<>(selectClause, fromClause, whereClause);
        return query.apply();
    }
}
