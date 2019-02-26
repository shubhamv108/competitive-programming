package code.strings;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WhatIfABCwasntABC {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        int[] alpha = new int[26];
        for(int i=0;i<=25;i++) {
            alpha[i] = s1.charAt(i) - 'A';
        }
        for(int i=0;i<26;i++) {
            System.out.printf("%d ", i);
        }
        int[] string = new int[26];
        for(int i=0;i<s2.length();i++) {
            string[s2.charAt(i)-'A']++;
        }

        for(int i=0;i<26;i++) {
            for(int j=0;j<string[alpha[i]];j++) {
                System.out.printf("%c", (char)(alpha[i] + 'A'));
            }

        }
    }
}
