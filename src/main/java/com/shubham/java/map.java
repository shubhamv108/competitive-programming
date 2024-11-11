package com.shubham.java;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.*;

class VisitCounter {

    static class UserStats {
        final Optional<Long> visitCount;

        UserStats(Optional<Long> visitCount) {
            this.visitCount = visitCount;
        }

        public Optional<Long> getVisitCount() {
            return visitCount;
        }
    }

    Map<Long, Long> count(Map<String, UserStats>... visits) {
        Map<Long, Long> result = new HashMap<>();
        if (visits == null)
            return result;

        Arrays.stream(visits)
                .filter(Objects::nonNull)
                .filter(v -> !v.isEmpty())
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .filter(e -> e.getKey() != null)
                .filter(e -> e.getValue() != null)
                .filter(e -> e.getValue().getVisitCount() != null)
                .filter(e -> e.getValue().getVisitCount().isPresent())
                .filter(e -> {
                    try {
                        Long.valueOf(e.getKey());
                        return true;
                    } catch (NumberFormatException ex) {
                        return false;
                    }
                })
                .forEach(e -> {
                    Long userId = Long.valueOf(e.getKey());
                    result.put(userId, result.getOrDefault(userId, 0l) + e.getValue().getVisitCount().get());
                });
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                new VisitCounter().count(Map.of(
                        "1", new UserStats(Optional.of(11l)),
                        "2", new UserStats(Optional.of(23l)),
                        "3", new UserStats(Optional.of(35l)))));
    }
}