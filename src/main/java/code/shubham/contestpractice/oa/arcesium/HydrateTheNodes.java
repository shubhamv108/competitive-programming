package code.shubham.contestpractice.oa.arcesium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HydrateTheNodes {
    public class Solution {
        public int solve(int[] P, int[] W, int underHydratedPenalty, int overHydratedPenalty) {
            Map<Integer, List<Integer>> family = new HashMap<>();
            Map<Integer, int[]> count = new HashMap<>();
            for (int c = 0; c < P.length; c++) {
                List<Integer> children = family.get(P[c]);
                if (children == null)
                    family.put(P[c], children = new ArrayList<>());
                children.add(c);
            }

            Integer familyHead = family.get(-1).get(0);
            visit(familyHead, family, W, count);
            int ifNoWater = (count.get(familyHead)[0] * underHydratedPenalty) +
                            (count.get(familyHead)[1] * overHydratedPenalty);
            int result = ifNoWater;
//            for (int i = 0; i < P.length; i++) {
//                int currMax = 0;
//                currMax -= (count.get(i)[0] * underHydratedPenalty);
//                result = Math.min(result, (ifNoWater + currMax));
//            }

            int p = count.get(0)[0] * underHydratedPenalty;
            for (int[] v: count.values())
                result = Math.min(result, (v[1] * overHydratedPenalty) + (p - (v[0] * underHydratedPenalty)));

            return result;
        }

        int[] visit(int node, Map<Integer, List<Integer>> family, int[] W, Map<Integer, int[]> nodeCount) {
            int[] count = new int[2];
            List<Integer> children = family.get(node);
            if (children != null) {
                for (Integer child: children) {
                    int[] childCount  = visit(child, family, W, nodeCount);
                    count[0] += childCount[0];
                    count[1] += childCount[1];
                }
            }
            if (W[node] == -1) count[0]++;
            if (W[node] ==  1) count[1]++;
            nodeCount.put(node, count);
            return count;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new HydrateTheNodes().new Solution().solve(
                        new int[]{-1, 0, 0},
                        new int[]{1, -1, -1},
                        10,
                        15
                )
        );
    }
}
