package code.shubham.strings;

public class CustomSortString {

    public String getPermutation(String order, String s) {
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int i = 0;
        for (char c : order.toCharArray()) {
            if (i == chars.length) break;
            while (count[c-'a']-- > 0) {
                chars[i++] = c;
            }
        }
        for (int k = 0; k < count.length; k++) {
            while (count[k]-- > 0) {
                chars[i++] = (char) (k + 'a');
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(
                new CustomSortString().getPermutation("cba", "abcd")
        );
    }

}
