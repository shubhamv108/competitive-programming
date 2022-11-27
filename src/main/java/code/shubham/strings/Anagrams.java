package code.shubham.strings;

import java.util.ArrayList;

public class Anagrams {

    ArrayList<String> getAnagrams(String s) {
        ArrayList<String> anagrams = new ArrayList<>();
        populateAnagrams(s.toCharArray(), 0, anagrams);
        return anagrams;
    }

    private void populateAnagrams(char[] s, int index, ArrayList<String> anagrams) {
        if (index == s.length - 1) {
            anagrams.add(new String(s));
            return;
        }
        for (int i = index; i < s.length; i++) {
            swap(s, i, index);
            populateAnagrams(s, index+1, anagrams);
            swap(s, i, index);
        }
    }

    void swap(char[] s, int i, int j) {
        char t = s[i];
        s[i] = s[j];
        s[j] = t;
    }

    public static void main(String[] args) {
        System.out.println(
                new Anagrams().getAnagrams("abc")
        );
    }
}
