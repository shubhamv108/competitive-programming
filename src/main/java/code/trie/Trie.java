package code.trie;

public class Trie {

    TNode createAndGetNode() {
        TNode node = new TNode();
        node.isEOW = false;
        return node;
    }

    void insertWordInTrie(TNode root, String word) {
        for(char c : word.toCharArray()) {
            int a = (int) c;
            if(a > 64 && a < 91) a = a - 65;
            if(a > 96 && a < 122) a = a - 97;
            if(root.next[a] == null) root.next[a] = createAndGetNode();
            root = root.next[a];
         }
         root.isEOW = true;
    }

    void printWordsInTrieHelper(TNode root, char[] path, int pi) {
        if(root == null) return;
        if(root.isEOW) {
            for(int i=0;i<pi;i++) System.out.printf("%c", path[i]);
            System.out.printf(" ");
        }
        for(int i = 0; i < 26; i++) {
            path[pi] = (char) (i + 'a');
            printWordsInTrieHelper(root.next[i], path, pi+1);
        }
    }

    void printWordsInTrie(TNode root) { printWordsInTrieHelper(root, new char[101], 0); }

}
