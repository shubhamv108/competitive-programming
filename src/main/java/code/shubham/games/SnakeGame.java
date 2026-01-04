package code.shubham.games;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class SnakeGame {

    int R, C, f[][], fi, score;
    int r, c;
    LinkedHashSet<String> occupied;
    HashMap<String, int[]> m = new HashMap<>() {
        {
            put("R", new int[] {0, 1});
            put("L", new int[] {0, -1});
            put("U", new int[] {-1, 0});
            put("D", new int[] {1, 0});
        }
    };

    public SnakeGame(int width, int height, int[][] food) {
        C = width;
        R = height;
        f = food;
        occupied = new LinkedHashSet<>();
        occupied.add("0-0");
    }

    public int move(String direction) {
        int[] dir = m.get(direction);
        r += dir[0];
        c += dir[1];
        String rc = r + "-" + c;
        if (r < 0 || c < 0 || r == R || c == C)
            return -1;
        String removed = occupied.removeFirst();
        if (!occupied.add(rc))
            return -1;
        if (fi < f.length && f[fi][0] == r && f[fi][1] == c) {
            ++fi;
            ++score;
            occupied.addFirst(removed);
        }
        return score;
    }

    public static void main(String[] args) {
        var game = new SnakeGame(3, 3, new int[][] {
                {2,0},
                {0,0},
                {0,2},
                {2,2}
        });

        System.out.println(game.move("D"));
        System.out.println(game.move("D"));
        System.out.println(game.move("R"));
        System.out.println(game.move("U"));
        System.out.println(game.move("U"));
        System.out.println(game.move("L"));
        System.out.println(game.move("D"));
        System.out.println(game.move("R"));
        System.out.println(game.move("R"));
        System.out.println(game.move("U"));
        System.out.println(game.move("L"));
        System.out.println(game.move("D"));
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
