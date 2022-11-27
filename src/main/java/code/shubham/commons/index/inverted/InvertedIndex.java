package code.shubham.commons.index.inverted;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InvertedIndex {

    private final Map<Object, ColumnOccurrence> occurrences = new HashMap<>();

    public void index(String documentId, Object object) {
        this.index(documentId, object, new StringBuilder());
    }

    private void index(String documentId, Object object, StringBuilder name) {
        if (object == null)
            return;

        for (Field field: object.getClass().getDeclaredFields()) {
            if (Modifier.isTransient(field.getModifiers()))
                continue;

            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (value instanceof String) {
                name.append(field.getName());
                this.addOccurrence(documentId, name, (String) value);
                int startIndex = name.length() - field.getName().length();
                for (int index = name.length() - 1; index >= startIndex; index--)
                    name.deleteCharAt(index);
            }
        }
    }

    private void addOccurrence(String documentId, StringBuilder nameBuilder, String value) {
        String name = nameBuilder.toString();
        ColumnOccurrence columnOccurrence = this.occurrences.get(value);
        if (columnOccurrence == null)
            this.occurrences.put(name, columnOccurrence = new ColumnOccurrence());
        columnOccurrence.add(documentId, name);
    }

    public Collection<String> filter(Object filter) {
        return filter(filter, new StringBuilder());
    }

    private Collection<String> filter(Object filter, StringBuilder name) {
        if (filter instanceof Map<?, ?>)
            return filter((Map<?, ?>) filter, name);
        if (filter instanceof Collection<?>)
            return filter((Collection<?>) filter, name);
        if (filter.getClass().isArray())
            return filter((Object[]) filter, name);
        return filterOnValue(filter, name.toString());
    }

    private Collection<String> filter(Map<?, ?> filter, StringBuilder name) {
        if (name == null)
            name = new StringBuilder();
        Collection<String> result = new ArrayList<>(), documentIds;
        for (Map.Entry<?, ?> entry: filter.entrySet()) {
            documentIds = filter(entry.getValue(), name.append(entry.getKey()));
            if (result.isEmpty())
                result.addAll(documentIds);
            for (String documentId: result)
                if (!documentIds.contains(documentId))
                    result.remove(documentId);
        }
        return result;
    }

    private Collection<String> filter(Collection<?> filter, StringBuilder name) {
        Set<String> ids = new HashSet<>();
        for (Object value: filter)
            ids.addAll(filter(value, name));
        return ids;
    }

    private Collection<String> filter(Object[] filter, StringBuilder name) {
        Set<String> ids = new HashSet<>();
        for (Object value: filter)
            ids.addAll(filter(value, name));
        return ids;
    }

    private List<String> filterOnValue(Object value, String name) {
        ColumnOccurrence columnOccurrence = this.occurrences.get(value);
        if (columnOccurrence == null)
            return new ArrayList<>();
        return columnOccurrence.filter(name);
    }
}
