package code.shubham.strings;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FirstNonRepeatedCharacterInString {

    private class Solution {
        Character solve(final String s) {
            return IntStream.range(0, s.length())
                    .mapToObj(s::charAt)
                    .collect(
                            Collectors.groupingBy(
                                    Function.identity(),
                                    LinkedHashMap::new,
                                    Collectors.counting()))
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() == 1)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(' ');
        }
    }
    public static void main(String[] args) {
        final FirstNonRepeatedCharacterInString problem = new FirstNonRepeatedCharacterInString();
        System.out.println(problem.new Solution().solve("31243"));
        System.out.println(problem.new Solution().solve("31241"));
    }

}
