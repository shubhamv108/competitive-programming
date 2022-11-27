package code.shubham.contestpractice.oa.amazon;

public class RobotBoundedInCircle {

    class Solution {
        int x = 0, y = 0, r = 0, directions[][] = {{0, 1}, {1, 0}, {0, -1}, { -1, 0}};
        public boolean isRobotBounded(String instructions) {
            char[] chrs = instructions.toCharArray();
            for (char c : chrs)
                if (c == 'R')
                    r = (r + 1) % 4;
                else if (c == 'L')
                    r = (r + 3) % 4;
                else {
                    x += directions[r][0];
                    y += directions[r][1];
                }
            return x == 0 && y == 0 || r > 0;
        }
    }

}
