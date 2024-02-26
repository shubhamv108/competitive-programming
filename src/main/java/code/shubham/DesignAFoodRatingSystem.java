package code.shubham;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class DesignAFoodRatingSystem {
    class FoodRatings {
        HashMap<String, Comparable> r = new HashMap<>();
        HashMap<String, String> c = new HashMap<>();

        HashMap<String, PriorityQueue<Comparable[]>> m = new HashMap<>();
        Comparator<Comparable[]> ratingComparator = (x, y) -> y[1].compareTo(x[1]);
        Comparator<Comparable[]> nameComparator = (x, y) ->  x[0].compareTo(y[0]);
        Comparator<Comparable[]> orderComparator = ratingComparator.thenComparing(nameComparator);

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            for (int i = 0; i < foods.length; ++i) {
                r.put(foods[i], ratings[i]);
                c.put(foods[i], cuisines[i]);
                m.computeIfAbsent(cuisines[i], e -> new PriorityQueue<>(orderComparator))
                        .add(new Comparable[] { foods[i], ratings[i] });
            }
        }

        public void changeRating(String food, int newRating) {
            r.put(food, newRating);
            m.get(c.get(food)).add(new Comparable[] { food, newRating });
        }

        public String highestRated(String cuisine) {
            Comparable[] p = m.get(cuisine).peek();
            while (r.get((String) p[0]).compareTo(p[1]) != 0) {
                m.get(cuisine).poll();
                p = m.get(cuisine).peek();
            }

            return (String) p[0];
        }
    }

    class FoodRatings2 {
        HashMap<Comparable, Comparable> r = new HashMap<>();
        HashMap<String, String> c = new HashMap<>();

        HashMap<String, TreeSet<Comparable[]>> m = new HashMap<>();
        Comparator<Comparable[]> ratingComparator = (x, y) -> y[1].compareTo(x[1]);
        Comparator<Comparable[]> nameComparator = (x, y) -> x[0].compareTo(y[0]);
        Comparator<Comparable[]> orderComparator = ratingComparator.thenComparing(nameComparator);

        public FoodRatings2(String[] foods, String[] cuisines, int[] ratings) {
            for (int i = 0; i < foods.length; ++i) {
                r.put(foods[i], ratings[i]);
                c.put(foods[i], cuisines[i]);
                m.computeIfAbsent(cuisines[i], e -> new TreeSet<>(orderComparator))
                        .add(new Comparable[] { foods[i], ratings[i] });
            }
        }

        public void changeRating(String food, int newRating) {
            TreeSet<Comparable[]> set = m.get(c.get(food));
            set.remove(new Comparable[] { food, r.get(food) });
            r.put(food, newRating);
            set.add(new Comparable[] { food, newRating });
        }

        public String highestRated(String cuisine) {
            return (String) m.get(cuisine).first()[0];
        }
    }

    public static void main(String[] args) {
//         FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
//         obj.changeRating(food,newRating);
//         String param_2 = obj.highestRated(cuisine);
//         System.out.println(param_2);
    }

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
}
