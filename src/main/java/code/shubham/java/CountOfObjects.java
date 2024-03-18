package code.shubham.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountOfObjects {

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

    class FindOut extends Thread {
        private static int x;
        public synchronized void doThings() {
            int current = x;
            ++current;
            x = current;
        }

        @Override
        public void run() {
            doThings();
        }
    }

    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        a.trimToSize();
    }

}
