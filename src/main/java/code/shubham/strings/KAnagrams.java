package code.shubham.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KAnagrams {

    static boolean areKAnagrams(String first, String second, int k) {
        Map<Character, Integer> map = new HashMap<>();
        char ch;
        int n;
        for(int i=0;i<first.length();i++) {
            ch = first.charAt(i);
            if(map.containsKey(ch)) {
                n = map.get(ch);
                map.put(ch, ++n);
            } else {
                map.put(ch, 1);
            }
        }
        int c = 0;
        for(int i=0;i<second.length();i++) {
            ch = second.charAt(i);
            if(map.containsKey(ch)) {
                n = map.get(ch);
                if(n == 0)
                    c++;
                else
                    map.put(ch, --n);
            } else {
                c++;
            }
        }
        return c <= k;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.next());
        while(t-- > 0) {
            int k = Integer.parseInt(sc.next());
            String s1 = sc.next();
            String s2 = sc.next();
            if(areKAnagrams(s1, s2, k))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
