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

    public class Codec {

        // Encodes a list of strings to a single string.
        public String encode(List<String> A) {
            StringBuilder result = new StringBuilder();
            A.stream().forEach(a -> result.append(a).append("π"));
            return result.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            var a = s.split("π", -1);
            return Arrays.asList(a).subList(0, a.length - 1);
        }
    }

    public class Codec2 {

        // Encodes a list of strings to a single string.
        public String encode(List<String> A) {
            StringBuilder result = new StringBuilder();
            A.stream().forEach(a -> result
                    .append(a.length())
                    .append("/:")
                    .append(a));
            return result.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            List<String> result = new ArrayList<>();
            int i = 0;
            while (i < s.length()) {
                int idx = s.indexOf("/:", i);
                int length = Integer.parseInt(s.substring(i, idx));
                result.add(s.substring(idx + 2, idx + 2 + length));
                i = idx + 2 + length;
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