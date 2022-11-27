package code.shubham.commons.index.inverted;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Occurrence {
    private int count = 0;
    private final Set<String> ids = new HashSet<>();

    protected void add(String documentId) {
        this.count++;
        this.ids.add(documentId);
    }

    public List<String> getIds() {
        return new ArrayList<>(ids);
    }
}
