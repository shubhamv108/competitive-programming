package code.shubham.trie;

import java.util.Arrays;

public class SuffixTrie {

    private class SuffixTrieNode {
        SuffixTrieNode[] characters;
        String suffix;
        boolean isEOW;
        void setWord(String word) {
            if (this.suffix == null && this.characters == null) {
                this.suffix = word;
            } else {
                if (this.suffix != null) {
                    this.characters = new SuffixTrieNode[26];
                    int a = ((int) this.suffix.charAt(0)) - 97;
                    this.characters[a] = new SuffixTrieNode();
                    if (this.suffix.length() > 1) {
                        this.characters[a].setWord(this.suffix.substring(1));
                    } else {
                        this.isEOW = true;
                    }
                    this.suffix = null;
                }
                int a = ((int) word.charAt(0)) - 97;
                if (this.characters[a] == null) this.characters[a] = new SuffixTrieNode();
                if (word.length() > 1) {
                    this.characters[a].setWord(word.substring(1));
                } else {
                    this.characters[a].isEOW = true;
                }
            }
        }

        boolean isWordPresent(String word) {
            if (this.suffix != null && this.suffix.equals(word)) {
                return true;
            }
            int a = ((int) word.charAt(0)) - 97;
            if (this.characters[a] != null && word.length() > 1) {
                return this.characters[a].isWordPresent(word.substring(1));
            } else {
                return this.characters[a].isEOW;
            }
        }
    }

    public static void main(String[] args) {
        SuffixTrieNode root = new SuffixTrie().new SuffixTrieNode();
        Arrays.asList("apple", "a", "app", "appilcation", "banana").forEach(root::setWord);
        System.out.println(root.isWordPresent("appl"));
    }

}
