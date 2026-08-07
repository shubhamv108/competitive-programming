package code.shubham.dynamicprogramming.gametheory;

/**
 * int solve(state) {
 *     if (terminal) return 0;
 *
 *     int best = -INF;
 *
 *     for (Move move : moves) {
 *         best = Math.max(best, gain(move) - solve(nextState(move)));
 *     }
 *
 *     return best;
 * }
 */
public class StoneGameIII {

}
