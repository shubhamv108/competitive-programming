package code.shubham.heapsmaps;

import java.util.HashMap;
import java.util.TreeSet;

class AuctionSystem {

    HashMap<Integer, Pair> m = new HashMap<>();

    public void addBid(int userId, int itemId, int bidAmount) {
        m.computeIfAbsent(itemId, e -> new Pair()).add(userId, bidAmount);
    }

    public void updateBid(int userId, int itemId, int newAmount) {
        m.get(itemId).update(userId, newAmount);
    }

    public void removeBid(int userId, int itemId) {
        m
                .get(itemId)
                .remove(userId);
    }

    public int getHighestBidder(int itemId) {
        return m.get(itemId).getHighest();
    }
}

class Pair {
    TreeSet<Integer> userIds = new TreeSet<>();
    HashMap<Integer, Integer> bids;

    Pair () {
        bids = new HashMap<>();
        userIds = new TreeSet<>(((x, y) -> bids.get(y) == bids.get(x) ?  y - x : bids.get(y) - bids.get(x)));
    }

    void add(int u , int b) {
        bids.put(u, b);
        userIds.add(u);
    }

    void update(int u , int b) {
        bids.put(u, b);
        userIds.remove(u);
        userIds.add(u);
    }

    void remove(int u) {
        userIds.remove(u);
        bids.remove(u);
    }

    int getHighest() {
        return userIds.stream().findFirst().orElse(-1);
    }
}

public class DesignAuctionSystem {
    public static void main(String[] args) {
        var system = new AuctionSystem();
        system.addBid(5, 4, 29);
        system.addBid(10, 4, 5);
        system.removeBid(10, 4);
        system.addBid(8, 2, 7);
        system.removeBid(8, 2);
        System.out.println(system.getHighestBidder(4));
    }
}