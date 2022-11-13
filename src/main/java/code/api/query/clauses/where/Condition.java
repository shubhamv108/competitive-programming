package code.api.query.clauses.where;

import code.api.Data;
import code.api.ReflectionUtils;

import java.util.Arrays;

public class Condition {
    String[] fieldPath;
    Operation operation;
    String[] values;

    boolean apply(Data data) throws IllegalAccessException {
        Object dataValue = ReflectionUtils.getValueForField(fieldPath, 0, data);
        if (Operation.EQUALS.equals(operation)) {
            return values[0].equals(dataValue);
        }
        if (Operation.IN.equals(operation)) {
            return Arrays.stream(values).anyMatch(value -> value.equals(dataValue));
        }
        return false;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "fieldPath=" + Arrays.toString(fieldPath) +
                ", operation=" + operation +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
