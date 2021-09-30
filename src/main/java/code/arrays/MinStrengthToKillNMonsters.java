package code.arrays;

public class MinStrengthToKillNMonsters {

    class Solution {
        int solve(int[][] cells, int[][] confidences) {
            int n = cells.length, m = cells[0].length, curStrength = 0, startingStrength = 0;
            for (int i = 0; i < n; i++) {
                int[] monsters = cells[i];
                int[] confidence = confidences[i];
                int minCost = Integer.MAX_VALUE;
                int minCostMonster = 0;
                for (int j = 0; j < m; j++) {
                    int cost = monsters[j] - confidence[j];
                    if (cost < minCost) {
                        minCost = cost;
                        minCostMonster = monsters[j];
                    }
                }
                if (minCostMonster > curStrength) {
                    startingStrength += (minCostMonster - curStrength);
                    curStrength = minCostMonster;
                }
                curStrength -= minCost;
            }
            return startingStrength;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new MinStrengthToKillNMonsters()
                        .new Solution()
                            .solve(new int[][] {
                                        {3, 2, 5},
                                        {8, 9, 1},
                                        { 4, 7, 6}
                                    },
                                    new int[][] {
                                        {1, 1, 1},
                                        {1, 1, 1},
                                        {1, 1, 1}
                                    }
                            )
        );
    }

}
