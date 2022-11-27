package code.shubham.commons.query.clauses.from;

import java.util.Collection;

public class FromClause<Data> {

    private final Collection<Data> from;

    public FromClause(Collection<Data> from) {
        this.from = from;
    }

    public Collection<Data> getFrom() {
        return from;
    }
}
