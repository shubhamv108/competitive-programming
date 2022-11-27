package code.shubham.commons.strategies.filter;

import java.util.Collection;
import java.util.Map;

public interface FilterStrategy<Data> {
    void add(Data data);
    void add(String documentId, Data data);
    Collection<Data> filter(Map<String, Object> filter);
}
