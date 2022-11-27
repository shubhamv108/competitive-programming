package code.shubham.strings;

import java.util.function.Function;

public class RomanToInt {
    class Solution {
        private Character romanChar ( int n ) {
            switch (n) {
                case 1    :   return 'I';
                case 5    :   return 'V';
                case 10   :   return 'X';
                case 50   :   return 'L';
                case 100  :   return 'C';
                case 500  :   return 'D';
                case 1000 :   return 'M';
                default   :   return Character.MIN_VALUE;
            }
        }

        public String intToRoman (int A) {
            return null;
        }

    }
}
