package code.api.query.clauses.where;

import code.api.Address;
import code.api.Data;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Condition {
    String[] fieldPath;
    Operation operation;
    String[] values;

    boolean apply(Data data) throws IllegalAccessException {
        Object dataValue = this.getValueForField(fieldPath, 0, data);
        if (Operation.EQUALS.equals(operation)) {
            return values[0].equals(dataValue);
        }
        if (Operation.IN.equals(operation)) {
            return Arrays.stream(values).anyMatch(value -> value.equals(dataValue));
        }
        return false;
    }

    private static Object getValueForField(String[] fieldPath, int index, Object object)
            throws IllegalAccessException {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.getName().equals(fieldPath[index])) {
                field.setAccessible(true);
                if (index == fieldPath.length - 1)
                    return field.get(object);
                return getValueForField(fieldPath, index + 1, field.get(object));
            }
        }
        return "";
    }

    public static void main(String[] args) throws IllegalAccessException {
        Data data = new Data(new Address("test"));
        System.out.println(
                getValueForField(new String[] {"address", "city"}, 0, data));
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
