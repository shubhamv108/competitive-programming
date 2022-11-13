package code.api;

import java.lang.reflect.Field;

public class ReflectionUtils {

    public static Object getValueForDotSeperatedFieldName(String fieldName, Object object)
            throws IllegalAccessException {
        return ReflectionUtils.getValueForField(fieldName, "\\.", object);
    }

    public static Object getValueForField(String fieldName, String seperatorRegex, Object object)
            throws IllegalAccessException {
        String[] fieldPath = fieldName.split(seperatorRegex);
        return ReflectionUtils.getValueForField(fieldPath, 0, object);
    }
    public static Object getValueForField(String[] fieldPath, int index, Object object)
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
                ReflectionUtils.getValueForField(new String[] {"address", "city"}, 0, data));
    }
}
