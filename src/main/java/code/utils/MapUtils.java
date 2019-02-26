package code.utils;

import java.util.Map;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class MapUtils {

    public boolean isNullOrEmpty(Map m) {
        return null == m || m.isEmpty();
    }

    public static Integer[] maptoIntegerArray(String... s) {
        return IntStream.range(0, s.length).mapToObj(i -> parseInt(s[i])).toArray(Integer[]::new);
    }

}
