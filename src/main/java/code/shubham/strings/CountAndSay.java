package code.shubham.strings;

public class CountAndSay {
    public class Solution {
        public String countAndSay(int A) {
            StringBuilder result = null;
            String a = "1";
            if (A == 1) return a;
            while (A-- > 1) {
                result = new StringBuilder();
                char[] c = a.toCharArray();
                int count = 1;
                if (c.length == 1) {
                    result.append('1');
                    result.append(c[0]);
                } else {
                    for (int i = 1; i <= c.length; i++) {
                        if (i < c.length && c[i] == c[i-1]) {
                            count++;
                        } else {
                            result.append(count);
                            result.append(c[i-1]);
                            System.out.println("A="+A+" "+result.toString());
                            count = 1;
                        }
                    }
                }
                a = result.toString();
                System.out.println(a);
            }
            return a;
        }
    }

    public static void main(String[] args) {
        System.out.println(
        new CountAndSay().new Solution().countAndSay(4));
    }
}
