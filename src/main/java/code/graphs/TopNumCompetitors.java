package code.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class TopNumCompetitors {
    public List<String> topNumCompetitors(int numCompetitors,
                                          int topNCompetitors,
                                          List<String> competitorsList,
                                          int numReviews, List<String> reviews) {
        Map<String, Integer> competitors = new HashMap<>();
        for (String competitor : competitorsList) competitors.put(competitor, 0);
        for (String review : reviews) {
            for (String word : review.split("\\s")) {
                if (competitors.containsKey(word)) {
                    competitors.put(word, competitors.getOrDefault(word, 0) + 1);
                }
            }
        }
        PriorityQueue<Map.Entry<String, Integer>> q = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue()
                        ? b.getKey().compareTo(a.getKey())
                        : a.getValue() - b.getValue()
        );
        for (Map.Entry<String, Integer> entry : competitors.entrySet()) {
            q.offer(entry);
            if (q.size() > topNCompetitors) {
                q.poll();
            }
        }
        return q.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> reviews = new ArrayList(
                Arrays.asList("newshop is providing good services in the city; everyone should use newshop",
                        "best services by newshop", "fashionbeats has great services in the city",
                        "I am proud to have fashionbeats", "mymarket has awesome services",
                        "Thanks Newshop for the quick delivery"));

        List<String> competitors = new ArrayList<String>(
                Arrays.asList("newshop", "shopnow", "afashion", "fashionbeats", "mymarket", "tcellular"));

        System.out.println(new TopNumCompetitors().topNumCompetitors(6, 2, competitors, 6, reviews));
    }
}
