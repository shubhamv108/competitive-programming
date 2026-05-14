package code.shubham.arrays;

import java.util.HashSet;

public class CompromisedSubarrayCount {

    public class Solution {

        public static long getCompromisedSubarrayCount(int[] arr) {
            int n = arr.length;
            long result = 0;

            for (int i = 0; i < n; ++i) {
                int currentOR = 0;
                HashSet<Integer> elements = new HashSet<>();

                for (int j = i; j < n; ++j) {
                    int newOR = currentOR | arr[j];

                    // If OR doesn't change, further expansion won't change OR
                    if (newOR == currentOR) {
                        if (elements.contains(currentOR))
                            result += (n - j);
                        break;
                    }

                    currentOR = newOR;
                    elements.add(arr[j]);

                    if (elements.contains(currentOR))
                        ++result;
                }
            }

            return result;
        }

    }

    static void main(String[] args) {
        System.out.println(new CompromisedSubarrayCount().new Solution().getCompromisedSubarrayCount(new int[] { 2, 4, 7 })); // 5
    }
}
