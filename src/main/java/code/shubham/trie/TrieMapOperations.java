package code.shubham.trie;

import java.util.Scanner;

public class TrieMapOperations
{

    TrieNode createAndGetNode() {
        TrieNode node = new TrieNode();
        node.isEOW = false;
        return node;
    }

    void PUT(TrieNode root, String key, String value) {
        for(char c : key.toCharArray()) {
            int a = Character.toLowerCase(c) - 'a';
            if(root.next[a] == null) root.next[a] = createAndGetNode();
            root = root.next[a];
        }
        root.isEOW = true;
        root.meaning = value;
    }

    String GET(TrieNode root, String key) {
        for(char c : key.toCharArray()) {
            int a = Character.toLowerCase(c) - 'a';
            if(root.next[a] == null) return "";
            root = root.next[a];
        }
        if(root.isEOW) return root.meaning;
        else return "";
    }

    boolean CHECK(TrieNode root, String key) {
        for(char c : key.toCharArray()) {
            int a = Character.toLowerCase(c) - 'a';
            if(root.next[a] == null) return false;
            root = root.next[a];
        }
        if(root.isEOW) return true;
        else return false;
    }

    boolean ISPREFIX(TrieNode root, String key)
    {
        for(char c : key.toCharArray()) {
            int a = Character.toLowerCase(c) - 'a';
            if(root.next[a] == null) return false;
            root = root.next[a];
        }
        for(int i=0;i<26;i++) {
            if(root.next[i] != null) return true;
        }
        return false;
    }

    void REMOVE(TrieNode root, String key)
    {
        for(int i =0;i<key.length();i++)
        {
            int idx = Character.toLowerCase(key.charAt(i)) - 'a';
            if(root.next[idx]==null)
                return;
            root = root.next[idx];

        }
        root.isEOW = false;
    }
}

class Test
{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        int tests = sc.nextInt();
        for(int ti=1;ti<=tests;ti++)
        {
            TrieNode root = new TrieNode();
            int opsCount = sc.nextInt();

            TrieMapOperations bzmops = new TrieMapOperations();
            for(int op=1;op<=opsCount;op++)
            {
                String operation = sc.next();
                String key = sc.next();

                if(operation.equals("PUT")){
                    String value = sc.next();
                    bzmops.PUT(root, key, value);
                }
                else if(operation.equals("GET"))
                    System.out.printf("%s ", bzmops.GET(root, key));
                else if(operation.equals("ISPREFIX"))
                    System.out.printf("%b ", bzmops.ISPREFIX(root, key));
                else if(operation.equals("CHECK"))
                    System.out.printf("%b ", bzmops.CHECK(root, key));
                else if(operation.equals("REMOVE"))
                    bzmops.REMOVE(root, key);
            }
            System.out.println();
        }
    }
}
