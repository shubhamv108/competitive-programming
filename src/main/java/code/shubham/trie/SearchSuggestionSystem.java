package code.shubham.trie;

import java.util.ArrayList;
import java.util.List;

public class SearchSuggestionSystem {
    class Solution {
        public List<List<String>> suggestedProducts(String[] P, String searchWord) {
            Node trie = new Node();
            for (String p: P)
                trie.add(p);

            return new TrieSearcher(trie, searchWord).getResult();
        }
    }

    class Node {
        ArrayList<String> cached = new ArrayList<>();
        Node[] next = new Node[26];

        void add(String word) {
            add(word, word.toCharArray(), 0);
        }

        void add(String W, char[] word, int index) {
            if (index == word.length)
                return;

            int nextIndex = word[index] - 'a';
            if (next[nextIndex] == null)
                next[nextIndex] = new Node();
            next[nextIndex].add(W, word, index+1);

            ArrayList<String> cached = next[nextIndex].cached;
            boolean isInserted = false;
            for (int i = 0; i < cached.size(); i++) {
                if (W.compareTo(cached.get(i)) < 0) {
                    if (cached.size() == 3)
                        cached.remove(2);
                    cached.add(i, W);
                    isInserted = true;
                    break;
                }
            }
            if (isInserted || cached.size() == 3)
                return;
            cached.add(W);
        }

        ArrayList<String> getSuggestions() {
            return cached;
        }
    }

    class TrieSearcher {
        Node root;
        char[] word;
        ArrayList<List<String>> result = new ArrayList<>();

        TrieSearcher(Node root, String word) {
            this.root = root;
            this.word = word.toCharArray();
        }

        void search() {
            search(root, 0);
        }

        void search(Node node, int index) {
            if (index == word.length)
                return;
            int nextIndex = word[index] - 'a';
            if (node.next[nextIndex] == null) {
                result.add(new ArrayList<>());
                return;
            }
            result.add(node.next[nextIndex].getSuggestions());
            search(node.next[nextIndex], index + 1);
        }

        ArrayList<List<String>> getResult() {
            search();
            while (result.size() < word.length)
                result.add(new ArrayList<>());
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new SearchSuggestionSystem().new Solution().suggestedProducts(
                    new String[] { "code","codephone","coddle","coddles","codes" },
                    "coddle"
            ));
    }
}
