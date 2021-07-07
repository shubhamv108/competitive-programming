package code.strings;

public class MaxProductOfWordLengthsInWholeArray {
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) return 0;

        int[] wordCharacterSetBits = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                wordCharacterSetBits[i] |= 1 << (c - 'a');
            }
        }
        int l1 = 0, l2 = 0;
        int shareCommonLetters = 0;
        boolean flag;
        for (int i = 0; i < words.length; i++) {
            if ((shareCommonLetters & (1 << (i))) == 1) {
                continue;
            }
            flag = false;
            for (int j = i + 1; j < words.length; j++) {
                if ((wordCharacterSetBits[i] & wordCharacterSetBits[j]) == 0) {
                    continue;
                } else {
                    shareCommonLetters |= 1 << (j);
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                if (l1 < words[i].length()) {
                    l2 = l1;
                    l2 = words[i].length();
                } else if (l2 < words[i].length()) {
                    l2 = words[i].length();
                }
            }
        }

        return l1 * l2;
    }

    public static void main(String[] args) {
//        System.out.println(
//        new MaxProductOfWordLengthsInWholeArray().maxProduct(new String[] {"abcw","baz","foo","bar","xtfn","abcdef"}));
    }
}
