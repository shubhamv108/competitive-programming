package code.shubham.stacksqueues;

import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElement {

    public int[] get(int[] nums1, int[] nums2) {
        Stack<Integer> s = new Stack<>();
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int i = nums2.length-1; i > -1; i--) {
            while(!s.isEmpty() && s.peek() <= nums2[i]) {
                s.pop();
            }

            if (!s.isEmpty()) {
                m.put(nums2[i], s.peek());
            }
            s.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = m.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }

}
