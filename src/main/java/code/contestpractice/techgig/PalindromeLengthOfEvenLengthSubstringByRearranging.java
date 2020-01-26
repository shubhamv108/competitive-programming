package code.contestpractice.techgig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;

public class PalindromeLengthOfEvenLengthSubstringByRearranging {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int maxSize = 0;
        char c;
        Map<Character, Integer> charMap = null;
        for (int i = 0; i < s.length() - 1; i++) {
            charMap = new HashMap<>();
            for (int j = i ; j < s.length(); j++) {
                c = s.charAt(j);
                charMap.compute(c, (k, v) -> v == null ? 1 : v + 1);
                if (charMap.values().stream().filter(charCount -> (charCount & 1) == 1).count() < 1) {
                    int size = charMap.values().stream().mapToInt(k -> k).sum();
                    maxSize = Math.max(maxSize, size);
                }
            }
            System.out.println(maxSize);
        }
    }

}
