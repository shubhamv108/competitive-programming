package code.shubham.java;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountofObjects {

    public <T> Map<T, Long> count(List<T> objects) {
        return objects.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public <T> List<T> duplicates(List<T> objects) {
        return objects.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
    }

}
