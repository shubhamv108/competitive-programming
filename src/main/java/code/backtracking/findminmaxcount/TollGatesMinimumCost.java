package code.backtracking.findminmaxcount;

public class TollGatesMinimumCost {

    static int minCostofTollTravelHelper(int[][] a, int R, int C, int r, int c, int cost) {
        if(c == C)
            return cost + a[r][c];
        int minPathCost = Integer.MAX_VALUE;
        int pathCost;
        for (int i = -1; i <= 1; i++) {
            int ri = r + i;
            if(ri >= 0 && ri <= R) {
                pathCost = minCostofTollTravelHelper(a, R, C, r + i, c + 1, cost + a[r][c]);
                minPathCost = Math.min(pathCost, minPathCost);
            }
        }
        return minPathCost;
    }

    static int minCostOfTollTravel(int cost[][], int startLane){
        return minCostofTollTravelHelper(cost, cost.length-1, cost[0].length-1, startLane - 1, 0, 0);
    }

    public static void main(String[] args) {
        int[][] tollGates = {
                {2,9,1,7},
                {3,0,1,-7},
                {6,2,1,8},
                {1,5,2,2},
                {5,4,1,1}
        };
        System.out.println(minCostOfTollTravel(tollGates, 5));
    }
}
