package code.shubham.commons.query.clauses.where;

import code.shubham.commons.utils.ReflectionUtils;

import java.util.Arrays;

public class Condition<Data> implements ICondition<Data> {
    String[] fieldPath;
    Operation operation;
    String[] values;

    public boolean test(Data data) {
        Object dataValue;
        try {
            dataValue = ReflectionUtils.getValueForField(fieldPath, 0, data);
        } catch (IllegalAccessException e) {
            return false;
        }
        if (Operation.EQUALS.equals(operation))
            return values[0].equals(dataValue);

        if (Operation.IN.equals(operation))
            return Arrays.stream(values).anyMatch(value -> value.equals(dataValue));

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
