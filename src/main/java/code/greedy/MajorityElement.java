package code.greedy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class MajorityElement {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int majorityElement(final List<Integer> A) {
        int majorityElement = -1;
        int majorityCount = ((Double) Math.floor(A.size()/2)).intValue();
        if (A.size() == 0) return majorityElement;
        Map<Integer, Integer> elementsCount = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            Integer c = elementsCount.get(A.get(i));
            if (c == null)
                c = 0;
            elementsCount.put(A.get(i), ++c);
            if (c > majorityCount) {
                majorityElement = A.get(i);
                break;
            }
        }
        return majorityElement;
    }

    public static void main(String[] args) {
        System.out.println(
            new MajorityElement().majorityElement(Arrays.asList(1, 1, 1, 2, 2)));
    }
}

class Solution2 {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int majorityElement(final List<Integer> A) {
        int majorityElement = -1;
        if (A.size() == 0) return majorityElement;
        int majorityCount = ((Double) Math.floor(A.size() / 2)).intValue();
        A.sort((a, b) -> a - b);
        return A.get(majorityCount);
    }
}


