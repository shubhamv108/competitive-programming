package code.shubham.contestpractice.oa.microsoft;

public class CheckIfAllAsAppearsBeforeAllBs {
    class Solution {
        public boolean checkString(String S) {
            boolean isAFound = false;
            char[] s = S.toCharArray();
            for (int i = s.length - 1; i > -1; i--)
                if (s[i] == 'a' && !isAFound)
                    isAFound = true;
                else if (s[i] == 'b' && isAFound)
                    return false;
            return true;
        }
    }
}
