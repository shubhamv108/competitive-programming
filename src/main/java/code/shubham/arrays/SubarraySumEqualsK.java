package code.shubham.arrays;

import java.util.HashMap;

public class SubarraySumEqualsK {
    public int subarraySum(int[] A, int k) {
        int result  = 0, sum = 0;
        HashMap<Integer, Integer> m = new HashMap<>();
        m.put(0, 1);
        for (int a : A) {
            sum += a;
            Integer c = m.getOrDefault(sum - k, 0);
            result += c;
            m.put(sum, m.getOrDefault(sum, 0) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new SubarraySumEqualsK().subarraySum(new int[] { 3, 2, 3, 2 }, 5 ));
    }

}