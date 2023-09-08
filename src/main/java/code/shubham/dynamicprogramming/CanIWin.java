package code.shubham.dynamicprogramming;

import java.util.HashSet;

public class CanIWin {
    class Solution {
        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            return canIWin(maxChoosableInteger, desiredTotal, new HashSet<>());
        }

        boolean canIWin(int maxChoosableInteger, int desiredTotal, HashSet<Integer> choosen) {
            if (desiredTotal <= 0)
                 return choosen.isEmpty() || (choosen.size() % 2) == 1;

            for (int i = Math.min(desiredTotal, maxChoosableInteger); i > 0; i--) {
                if (choosen.add(i)) {
                    if (canIWin(maxChoosableInteger, desiredTotal - i, choosen))
                        return true;
                    choosen.remove(i);
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new CanIWin().new Solution().canIWin(10, 11)
        );
    }
}
