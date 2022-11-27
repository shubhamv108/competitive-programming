package code.shubham.graphs.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class WordLadder {
    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Set<String> set = new HashSet<>();
            for (String word : wordList)
                set.add(word);
            if (!set.contains(endWord)) return 0;

            Queue<String> q = new LinkedList<>();
            q.offer(beginWord);
            int level = 1;
            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    String curWord = q.poll();
                    char[] chrs = curWord.toCharArray();
                    for (int i = 0; i < chrs.length; i++) {
                        char originalChar = chrs[i];
                        for (char c = 'a'; c <= 'z'; c++) {
                            chrs[i] = c;
                            String newWord = new String(chrs);
                            if (newWord.equals(endWord)) return level + 1;
                            if (set.contains(newWord)) {
                                q.offer(newWord);
                                set.remove(newWord);
                            }
                        }
                        chrs[i] = originalChar;
                    }
                }
                level++;
            }
            return 0;
        }
    }

}
