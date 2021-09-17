package code.graphs.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WorLadderII {

    // BFS (TLE)
    class Solution {

        class Path {
            LinkedHashSet<String> set = new LinkedHashSet<>();
            char[] lastWord;
            String lastWordString;
            Path() {}
            Path(LinkedHashSet<String> set) {
                this.set.addAll(set);
            }
            void addWord(String word) {
                this.set.add(word);
                this.lastWord = word.toCharArray();
                this.lastWordString = word;
            }
        }

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            List<List<String>> result = new ArrayList<>();
            if (wordList == null || wordList.isEmpty()) return result;
            if (beginWord.equals(endWord)) {
                result.add(new ArrayList<>(Arrays.asList(beginWord)));
                return result;
            }

            Set<String> set = new HashSet<>();
            for (String word : wordList)
                set.add(word);
            if (!set.contains(endWord)) return result;

            Queue<Path> q = new LinkedList<>();
            Path p = new Path();
            p.addWord(beginWord);
            q.offer(p);
            int minLevel = 1001;
            set.remove(beginWord);
            while (!q.isEmpty()) {
                if (q.peek().set.size() >= minLevel) break;
                int size = q.size();
                while (size-- > 0) {
                    p = q.poll();
                    char[] chrs = p.lastWord;
                    for (int i = 0; i < chrs.length; i++) {
                        char originalChar = chrs[i];
                        for (char c = 'a'; c <= 'z'; c++) {
                            chrs[i] = c;
                            String newWord = new String(chrs);
                            int newSize = p.set.size() + 1;
                            if (newWord.equals(endWord)) {
                                ArrayList<String> t = new ArrayList<>(p.set);
                                t.add(newWord);
                                if (newSize == minLevel) {
                                    result.add(t);
                                }
                                if (newSize < minLevel) {
                                    minLevel = newSize;
                                    result = new ArrayList<>();
                                    result.add(t);
                                }
                            } else if (newSize < minLevel && set.contains(newWord) && !p.set.contains(newWord)) {
                                Path newPath = new Path(p.set);
                                newPath.addWord(newWord);
                                q.offer(newPath);
                            }
                        }
                        chrs[i] = originalChar;
                    }
                    set.remove(p.lastWordString);
                }
            }
            return result;
        }
    }

}
