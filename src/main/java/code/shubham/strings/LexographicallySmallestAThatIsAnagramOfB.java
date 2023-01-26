package code.shubham.strings;

public class LexographicallySmallestAThatIsAnagramOfB {
    class Solution {
        String solve(String a, String b) {
            int[] af = new int[26];
            int[] bf = new int[26];

            for (int i = 0; i < a.length(); i++) {
                af[a.charAt(i) - 'a']++;
                bf[b.charAt(i) - 'a']++;
            }

            int[] extra = new int[26];
            int[] less = new int[26];
            for (int i = 0; i < 26; i++) {
                if (af[i] > bf[i])
                    extra[i] = af[i] - bf[i];
                else if (af[i] < bf[i])
                    less[i] = bf[i] - af[i];
            }

            char[] A = a.toCharArray();
            int lessIndex = 25;
            for (int i = A.length - 1; i > -1; i--) {
                if (A[i] == b.charAt(i))
                    continue;
                if (extra[A[i] - 'a'] > 0) {
                    extra[A[i] - 'a']--;
                    while (lessIndex > -1 && less[lessIndex] == 0)
                        lessIndex--;
                    less[lessIndex]--;
                    A[i] = (char) (lessIndex + 'a');
                }
            }

            return new String(A);
        }
    }

    class Solution2 {
        String solve(String a, String b) {
            int[] af = new int[26];
            int[] bf = new int[26];
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) == b.charAt(i))
                    continue;
                bf[b.charAt(i) - 'a']++;
            }

            char[] A = a.toCharArray();
            int bIndex = 0;
            for (int i = 0; i < A.length -1; i++) {
                if (A[i] == b.charAt(i))
                    continue;
                while (bIndex < 26 && bf[bIndex] == 0)
                    bIndex++;
                if (bIndex == 26)
                    break;
                A[i] = (char) (bIndex + 'a');
                bf[bIndex]--;
            }

            return new String(A);
        }
    }

    public static void main(String[] args) {
        var solution = new LexographicallySmallestAThatIsAnagramOfB()
                .new Solution2();
        System.out.println(solution.solve("bakakb", "kkkbab"));
        System.out.println(solution.solve("ovghk", "rpguc"));
        System.out.println(solution.solve("bakakbz", "kkkbabb"));
        System.out.println(solution.solve("bakakbz", "kkkbabb"));
        System.out.println(solution.solve("a", "b"));
        System.out.println(solution.solve("cbcbacbccca" +
                "babbacccaac" +
                "babbcbabbcb" +
                "accaaaccbcb" +
                "abcbca", "bbccabababa" +
                "caaaabbcaca" +
                "cbacaaacaca" +
                "bbaacccacbb" +
                "ccccaa"));
    }
    // aaaaacacccaaaabacccaacbabbcbabbcbaccaaaccbcbabcbca
    // abcaaaaaaaaaaaaaaacaabbbbbbbabbcbcccaccccccbccccca
}
