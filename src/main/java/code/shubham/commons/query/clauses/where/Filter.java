package code.shubham.commons.query.clauses.where;

import java.lang.reflect.Field;
import java.util.Map;

public class Filter<Data> implements ICondition<Data> {

    private final Map<String, Object> filter;

    public Filter(Map<String, Object> filter) {
        this.filter = filter;
    }


    @Override
    public boolean test(Data data) {
        try {
            return this.has(filter, data);
        } catch (IllegalAccessException e) {
            return false;
        }
    }

    public static <T> boolean has(Map<String, Object> fieldPath, Object object)
            throws IllegalAccessException {
        for (Map.Entry<String, Object> entry: fieldPath.entrySet()) {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.getName().equals(entry.getKey())) {
                    field.setAccessible(true);
                    if (entry.getValue() instanceof Map<?,?>)
                        return has((Map<String, Object>) entry.getValue(), field.get(object));
                    return entry.getValue().equals(field.get(object));
                }
            }
        }
        return false;
    }
}
