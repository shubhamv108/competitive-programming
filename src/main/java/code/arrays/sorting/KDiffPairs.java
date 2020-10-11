package code.arrays.sorting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
    public int findPairs(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        IntStream.range(0, nums.length).forEach(i -> map.put(nums[i], map.getOrDefault(nums[i], 0) + 1));
        System.out.println(map);
        int count = 0;
        Iterator<Map.Entry<Integer, Integer>> itr = map.entrySet().iterator();
        while (itr.hasNext()) {
            int n = itr.next().getKey();
            if (k == 0) {
                Integer c = map.get(n);
                if (c != null && c > 1) count++;
            } else {
                if (map.containsKey(n - k))
                    count++;
                if (map.containsKey(n + k))
                    count++;
            }
            itr.remove();
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution().findPairs(new int[] { 1,2,4,4,3,3,0,9,2,3 }, 3)
        );
    }
}