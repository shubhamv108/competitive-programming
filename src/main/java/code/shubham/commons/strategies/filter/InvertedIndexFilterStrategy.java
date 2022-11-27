package code.shubham.commons.strategies.filter;

import code.shubham.commons.index.inverted.InvertedIndex;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class InvertedIndexFilterStrategy<Data> implements FilterStrategy<Data> {

    private final InvertedIndex invertedIndex = new InvertedIndex();
    private final Map<String, Data> datas;

    public InvertedIndexFilterStrategy(Map<String, Data> datas) {
        this.datas = datas;
    }

    @Override
    public void add(Data data) {}

    @Override
    public void add(String documentId, Data data) {
        this.invertedIndex.index(documentId, data);
    }

    @Override
    public Collection<Data> filter(Map<String, Object> filter) {
        return this.invertedIndex.filter(filter).
                stream().
                map(datas::get).
                collect(Collectors.toList());
    }
}
