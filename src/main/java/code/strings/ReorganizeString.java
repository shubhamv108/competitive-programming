package code.strings;

import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class ReorganizeString {

    class Solution {
        public String reorganizeString(String s) {
            char[] c = s.toCharArray();
            int l = s.length();
            int[] count = new int[26];
            for (int i = 0; i < l; i++) {
                int chr = c[i] - 'a';
                count[chr]++;
                if (count[chr] > (l+1)/2) return "";
            }
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
            IntStream.range(0, 26).forEach(i -> {
                if (count[i] != 0)
                    pq.offer(new int[] { i, count[i] });
            });
            StringBuilder sb = new StringBuilder();
            while (!pq.isEmpty()) {
                int[] t = pq.poll();
                char chr = (char) (t[0] + 'a');
                if (sb.length() == 0 || sb.charAt(sb.length() - 1) != chr) {
                    sb.append(chr);
                    if (--t[1] > 0) {
                        pq.offer(t);
                    }
                } else {
                    int[] g = pq.poll();
                    chr = (char) (g[0] + 'a');
                    sb.append(chr);
                    if (--g[1] > 0) {
                        pq.offer(g);
                    }
                    pq.offer(t);
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new ReorganizeString().new Solution().reorganizeString("vvvlo")
        );
    }

}
