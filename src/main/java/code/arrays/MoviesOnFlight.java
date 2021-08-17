package code.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are on a flight and wanna watch two movies during this flight.
 * You are given List<Integer> movieDurations which includes all the movie durations.
 * You are also given the duration of the flight which is d in minutes.
 * Now, you need to pick two movies and the total duration of the two movies is less than or equal to (d - 30min).
 *
 * Find the pair of movies with the longest total duration and return they indexes.
 * If multiple found, return the pair with the longest movie.
 */
public class MoviesOnFlight {

    private static int[] get2SumClosest(int[] movie_duration, int d) {
        d = d - 30;
        Map<Integer, ArrayList<Integer>> movies = new HashMap<>();
        for (int i = 0; i < movie_duration.length; i++) {
            ArrayList<Integer> l = movies.get(movie_duration[i]);
            if (l == null) movies.put(movie_duration[i], l = new ArrayList<>());
            l.add(i);
        }
        Arrays.sort(movie_duration);
        int l = 0, r = movie_duration.length - 1;
        int[] result = new int[] {-1, -1};
        int max = 0;
        while (l < r) {
            int sum = movie_duration[l] + movie_duration[r];
            if (sum <= d) {
                if (sum > max
                    || (sum == max && Math.max(movie_duration[l], movie_duration[r]) > Math.max(result[0], result[1]))) {
                    max = sum;
                    result[0] = movie_duration[l];
                    result[1] = movie_duration[r];
                }
            }
            if (sum > d) r--;
            else l++;
        }
        boolean isEqual = result[0] == result[1];
        result[0] = movies.get(result[0]).get(movies.get(result[0]).size()-1);
        result[1] = movies.get(result[1]).get(movies.get(result[1]).size()- (isEqual ? 2 : 1));
        return result;
    }

    public static void main(String[] args) {
        Arrays.stream(
                get2SumClosest(new int[] {90, 85, 75, 60, 120, 150, 125}, 250)
        ).forEach(System.out::println);
    }

}
