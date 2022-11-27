package code.shubham.commons.index.inverted;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ColumnOccurrence extends Occurrence {
    private final Map<String, Occurrence> columnOccurrences = new HashMap<>();

    public void add(String documentId, String columnName) {
        this.add(documentId);
        Occurrence occurrence = this.columnOccurrences.get(columnName);
        if (occurrence == null)
            this.columnOccurrences.put(columnName, occurrence = new Occurrence());
        occurrence.add(documentId);
    }

    public List<String> filter(String name) {
        if (name == null || name.isEmpty())
            return new ArrayList<>(this.getIds());
        return Optional.ofNullable(columnOccurrences.get(name)).
                map(Occurrence::getIds).
                orElse(new ArrayList<>());
    }
}
