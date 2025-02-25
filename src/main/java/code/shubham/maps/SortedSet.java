package code.shubham.maps;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

public class SortedSet {

    private final Map<String, AtomicLong> memberToScores = new ConcurrentHashMap<>();
    private final Map<Long, Map<String, Void>> scoreToMember = new ConcurrentSkipListMap<>();

    public void zAdd(long score, String member) {
        AtomicLong currentScore = memberToScores.computeIfAbsent(member, _ -> new AtomicLong());
        scoreToMember.computeIfAbsent(currentScore.get(), _ -> new ConcurrentHashMap<>()).remove(member);
        currentScore.compareAndSet(0, score);
        scoreToMember.computeIfAbsent(currentScore.get(), _ -> new ConcurrentHashMap<>()).put(member, null);
    }

    public void zIncBy(long score, String member) {
        AtomicLong currentScore = memberToScores.computeIfAbsent(member, _ -> new AtomicLong());
        scoreToMember.computeIfAbsent(currentScore.get(), _ -> new ConcurrentHashMap<>()).remove(member);
        currentScore.compareAndSet(currentScore.get(), score);
        scoreToMember.computeIfAbsent(currentScore.get(), _ -> new ConcurrentHashMap<>()).put(member, null);
    }

}
