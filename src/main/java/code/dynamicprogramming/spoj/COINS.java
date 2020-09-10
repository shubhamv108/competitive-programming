package code.dynamicprogramming.spoj;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main
{
    static Map<Integer, Long> cache = new HashMap<>();

    static long solve(int n) {
        if(cache.containsKey(n)) return cache.get(n);
        long ans = Math.max(n, solve(n/2) + solve(n/3) + solve(n/4));
        cache.put(n, ans);
        return ans;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int n = Integer.parseInt(sc.next());
            cache.put(0, 0l);
            System.out.println(solve(n));
        }
    }
}