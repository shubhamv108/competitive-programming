package code.shubham.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromePairs {
    class Solution {
        public List<List<Integer>> palindromePairs(String[] words) {
            ArrayList<List<Integer>> result = new ArrayList<>();
            ArrayList<char[]> wordCharArrays = new ArrayList<>();
            for (String word : words)
                wordCharArrays.add(word.toCharArray());

            int i = 0, len = words.length;
            Node trie = new Node();
            for (; i < len; i++)
                trie.addWord(wordCharArrays.get(i), i);

            for (i = 0; i < len; i++) {
                ArrayList<Integer> indexes = (ArrayList<Integer>) trie.hasWordReverse(wordCharArrays.get(i));
                if (indexes == null)
                    continue;
                for (Integer j : indexes)
                    result.add(Arrays.asList(i, j));
            }

            return result;
        }

        class Node {
            Node[] next = new Node[26];
            ArrayList<Object> metaData;

            void addWord(String word, Object metaData) {
                addWord(word.toCharArray(), metaData);
            }

            void addWord(char[] word, Object metaData) {
                setNext(word, 0, metaData);
            }

            void setNext(char[] word, int index, Object metaData) {
                if (index == word.length) {
                    if (this.metaData == null)
                        this.metaData = new ArrayList<>();
                    this.metaData.add(metaData);
                    return;
                }

                int nextIndex = word[index] - 97;
                if (next[nextIndex] == null)
                    next[nextIndex] = new Node();

                next[nextIndex].setNext(word, index + 1, metaData);
            }

            Object hasWordReverse(char[] word) {
                return hasNextReverse(word, word.length - 1);
            }

            Object hasNextReverse(char[] word, int index) {
                if (index == -1)
                    return this.metaData;

                int nextIndex = word[index] - 97;
                if (next[nextIndex] == null)
                    return null;

                return next[nextIndex].hasNextReverse(word, index - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new PalindromePairs().new Solution().palindromePairs(
                        new String[] {
                                "abcd","dcba","lls","s","sssll"
//                                "lls"
                        }
                )
        );
    }
}
