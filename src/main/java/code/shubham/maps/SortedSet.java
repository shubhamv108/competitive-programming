package code.shubham.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;

public class SortedSet {

    private final HashMap<String, Long> memberToScores = new HashMap<>();
    private final TreeMap<Long, LinkedHashSet<String>> scoreToMembers = new TreeMap<>();

    public void incrBy(long score, String member) {
        Long currentScore = memberToScores.getOrDefault(member, 0L);
        scoreToMembers.computeIfAbsent(currentScore, _ -> new LinkedHashSet<>()).remove(member);
        currentScore += score;
        scoreToMembers.computeIfAbsent(currentScore, _ -> new LinkedHashSet<>()).add(member);
    }

    public int rank(String member) {
        Long currentScore = memberToScores.getOrDefault(member, 0L);
        return scoreToMembers.headMap(currentScore).size() + 1;
    }

    public List<List<List<String>>> range(String member, int rangeStart, int rangeEnd) {
        List<List<List<String>>> resp = new ArrayList<>();
        List<List<String>> prev = new ArrayList<>();
        List<List<String>> next = new ArrayList<>();
        resp.add(prev);
        resp.add(next);
        Long currentScore = memberToScores.getOrDefault(member, 0L);

        while (rangeStart++ < 0) {
            Long prevScore = scoreToMembers.lowerKey(currentScore);
            prev.add(new ArrayList<>(scoreToMembers.get(prevScore)));
        }

        while (rangeEnd-- < 0) {
            Long nextScore = scoreToMembers.lowerKey(currentScore);
            next.add(new ArrayList<>(scoreToMembers.get(nextScore)));
        }

        return resp;
    }

    void main() {
        
    }

}
