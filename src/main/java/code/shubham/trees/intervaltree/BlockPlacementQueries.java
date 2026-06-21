package code.shubham.trees.intervaltree;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class BlockPlacementQueries {
    class Solution {
        class Node {
            int start, end;
            Node (int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        public List<Boolean> getResults(int[][] Q) {
            List<Boolean> result = new ArrayList<>();

            TreeSet<Node> freeSpots = new TreeSet<>((i1, i2) -> {
                int lengthDiff = (i2.end - i2.start) - (i1.end - i1.start);
                return lengthDiff != 0 ? lengthDiff : i1.start - i2.start;
            });
            freeSpots.add(new Node(0, Integer.MAX_VALUE));

            TreeSet<Integer> obstacles = new TreeSet<>();
            for (int[] q : Q) {
                if (q[0] == 1)
                    addObstacle(obstacles, freeSpots, q[1]);
                else
                    result.add(canPlaceBlock(freeSpots, q[1], q[2]) ? Boolean.TRUE : Boolean.FALSE);
            }
            return result;
        }

        private void addObstacle(TreeSet<Integer> obstacles, TreeSet<Node> nodes, int obstacle) {
            Integer lowerBound = obstacles.floor(obstacle);
            if (lowerBound == null)
                lowerBound = 0;

            Integer upperBound = obstacles.ceiling(obstacle);
            if (upperBound == null)
                upperBound = Integer.MAX_VALUE;

            // replace one interval with two
            nodes.remove(new Node(lowerBound, upperBound));
            nodes.add(new Node(lowerBound, obstacle));
            nodes.add(new Node(obstacle, upperBound));

            // new obstacle
            obstacles.add(obstacle);
        }

        private boolean canPlaceBlock(TreeSet<Node> nodes, int x, int size) {
            Node current = nodes.first();
            while (current != null && current.end - current.start >= size) { // do not consider too short intervals
                if (Math.min(current.end, x) - current.start >= size) // ensure current.end is not too far right
                    return true;

                current = nodes.higher(current);
            }
            return false;
        }
    }
}
