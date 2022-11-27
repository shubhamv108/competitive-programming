package code.shubham.strings;

import java.util.HashSet;
import java.util.Set;

public class MinimumDeletionsToMakeCharacterFrequenciesUnique {

    public int minDeletions(String s) {
        int result = 0;
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c-'a']++;
        }
        Set<Integer> usedFrequency = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0 && !usedFrequency.add(count[i])) {
                count[i]--;
                result++;
            }
        }
        return result;
    }

}
