package code.shubham.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EncodeAndDecodeStrings {
    class Solution {
        /*
         * @param strs: a list of strings
         * @return: encodes a list of strings to a single string.
         */
        public String encode(List<String> A) {
            StringBuilder result = new StringBuilder();

            List<String> sizes = A
                    .stream()
                    .filter(Objects::nonNull)
                    .map(String::length)
                    .map(String::valueOf)
                    .collect(Collectors.toList());

            if (sizes.isEmpty())
                return "";

            return result
                .append(String.join(",", sizes))
                .append("#")
                .append(String.join("", A))
                .toString();
        }

        /*
         * @param str: A string
         * @return: decodes a single string to a list of strings
         */
        public List<String> decode(String A) {
            ArrayList<String> result = new ArrayList<>();
            if (A == null || A.length() == 0)
                return result;

            String[] a = A.split("#");
            int[] lengths = Arrays.stream(a[0].split(","))
                    .mapToInt(Integer::valueOf)
                    .toArray();
            String w = A.substring(a[0].length() + 1);

            int i = 0;
            for (int len : lengths) {
                result.add(w.substring(i, i + len));
                i += len;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        List<String> input = Arrays.asList("");
        System.out.println(
                new EncodeAndDecodeStrings()
                        .new Solution()
                        .decode(new EncodeAndDecodeStrings()
                                        .new Solution()
                                        .encode(input)));
    }
}