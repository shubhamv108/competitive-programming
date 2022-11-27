package code.shubham.strings;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class AToZCount {

    class Solution {

        ArrayList<Integer>[] indexes = new ArrayList[26];
        int largestDefault, smallestDefault = 0;

        Solution(String input) {
            this.largestDefault = input.length();
            char[] c = input.toCharArray();
            for (int i = 0; i < c.length; i++) {
                ArrayList<Integer> l = indexes[c[i] - 'a'];
                if (l == null) l = indexes[c[i] - 'a'] = new ArrayList<>();
                l.add(i);
            }
        }

        int getLargestIIndex(char ch, int k) {
            ArrayList<Integer> l = indexes[ch-'a'];
            if (l != null) {
                if (l.size() == k) return largestDefault;
                if (l.size() > k) {
                    return l.get(k) - 1;
                }
            }
            return -1;
        }

        int getSmallestIIndex(char ch, int k) {
            return Optional.ofNullable(indexes[ch-'a']).map(l -> {
                if (l.size()  >= k) {
                    return smallestDefault;
                }
                return -1;
            }).orElse(-1);
        }
    }

    public static void main(String[] args) {
        AToZCount aToZCount = new AToZCount();
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int q = sc.nextInt();
            String s =  sc.next();//abcfjbhbhbjbjb
            Solution solution = aToZCount.new Solution(s);
            while (q-- > 0) {
                if (sc.next().charAt(0) == 'L') {
                    System.out.println(solution.getLargestIIndex(sc.next().charAt(0), sc.nextInt()));
                } else {
                    System.out.println(solution.getSmallestIIndex(sc.next().charAt(0), sc.nextInt()));
                }
            }
        }
    }

}
