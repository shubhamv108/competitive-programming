package code.trees.strings;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import java.util.List;
import java.util.stream.Collectors;

class SearchSuggestionSystem {
    Node root = new Node();
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();

        char[] searched = searchWord.toCharArray();
        root.insert(-1, searched, 0, searchWord.length() - 1, true);
        Node first = root.suffixes.get(searched[0] - 'a');
        for (int i = 0; i < products.length; i++) {
            root.insert(i, products[i].toCharArray(), 0, products[i].length() - 1, false);
        }

        Node n = root;
        for (char c : searched) {
            n = n.suffixes.get(c-'a');
            System.out.println(String.format("%c->[%s]", c, String.join(",", n.words.stream().map(i -> products[i]).collect(Collectors.toList()))));
        }
        return result;
    }

    class Node {
        LinkedList<Integer> words = new LinkedList();

        Map<Integer, Node> suffixes = new HashMap<>();

        void insert(int index, char[] word, int i , int j, boolean isSearchedWord) {
            if (i > j) return;
            if (!isSearchedWord && words.size() < 3) words.add(index);
            char c = word[i];
            int key = c - 'a';
            Node n = suffixes.get(key);
            if (isSearchedWord && n == null) n = new Node();
            if (n != null) {
                n.insert(index, word, i + 1 , j, isSearchedWord);
                this.suffixes.put(key, n);
            }
            return;
        }
    }

    public static void main(String[] args) {
        new SearchSuggestionSystem().suggestedProducts(new String[] {"mobile","mouse","moneypot","monitor","mousepad"}, "mouse");
    }
}