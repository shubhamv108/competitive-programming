package code.paranthesis;

import java.util.Stack;

public class MinimumInsertionsToBalanceAParenthesesString {
    class Solution {
        public int minInsertions(String s) {
            char[] chrs = s.toCharArray();
            int stackSize = 0, i, openInsertions = 0, closeInsertions = 0;
            boolean isLastUtilized = false;
            for (i = 0; i < chrs.length - 1; i++) {
                if (chrs[i] == '(') {
                    stackSize++;
                    closeInsertions += 2;
                } else {
                    if (chrs[i+1] == ')') {
                        if (stackSize > 0) {
                            stackSize--;
                            closeInsertions-=2;
                        } else {
                            openInsertions+=1;
                        }
                        i++;
                        if (i == chrs.length - 1)
                            isLastUtilized = true;

                    } else {
                        if (stackSize > 0) {
                            stackSize--;
                            closeInsertions-=1;
                        } else {
                            openInsertions+=1;
                            closeInsertions+=1;
                        }
                    }
                }
            }

            if (chrs[chrs.length - 1] == '(') {
                closeInsertions += 2;
            } else if ( !isLastUtilized ) {
                if (stackSize > 0) {
                    closeInsertions-=1;
                } else {
                    openInsertions+=1;
                    closeInsertions+=1;
                }
            }

            return openInsertions + closeInsertions;
        }
    }

    class Solution2 {
        public int minInsertions(String s) {
            int ans = 0, cnt = 0;
            for(char ch : s.toCharArray()) {
                if (ch=='(') {
                    cnt+=2;
                    if ((cnt & 1) == 1){
                        ans++;
                        cnt--;
                    }
                } else {
                    cnt--;
                    if (cnt < 0){
                        ans++;
                        cnt += 2;
                    }
                }
            }

            return ans + cnt;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MinimumInsertionsToBalanceAParenthesesString().new Solution().minInsertions("))())(")
        );
    }
}
