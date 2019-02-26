package code.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PermutationsOfAString {
    void swap(char[] s, int a, int b) {
        char temp = s[a];
        s[a] = s[b];
        s[b] = temp;
    }

    void permutations(char[] s, int idx) {
        if(idx == s.length - 1) {
            for(int i=0;i<s.length; i++) {
                System.out.printf("%c", s[i]);
            }
            System.out.printf("\n");
            return;
        }
        for(int i=idx; i < s.length; i++) {
            swap(s, idx, i);
            permutations(s, idx+1);
            swap(s, idx, i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        char[] c = s.toCharArray();
        Arrays.sort(c);
        new PermutationsOfAString().permutations(c, 0);
    }
}
