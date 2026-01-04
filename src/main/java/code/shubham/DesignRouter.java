package code.shubham;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class Router {

    int ML;
    LinkedList<Long> Q = new LinkedList<>();
    HashMap<Long, int[]> P = new HashMap<>();
    HashMap<Integer, ArrayList<Integer>> destinationTimestamps = new HashMap<>();

    public Router(int memoryLimit) {
        ML = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        long key = encode(source, destination, timestamp);
        if (P.containsKey(key))
            return false;
        if (Q.size() == ML)
            forwardPacket();
        P.put(key, new int[] {source, destination, timestamp});
        Q.add(key);
        destinationTimestamps
                .computeIfAbsent(destination, _ -> new ArrayList<>())
                .add(timestamp);

        return true;
    }

    public int[] forwardPacket() {
        if (Q.isEmpty())
            return new int[0];
        Long n = Q.poll();
        int[] r = P.remove(n);
        destinationTimestamps.get(r[1]).remove(0);
        return r;
    }

    public int getCount(int destination, int startTime, int endTime) {
        ArrayList<Integer> t = destinationTimestamps.get(destination);
        if (t == null || t.isEmpty())
            return 0;
        return upper(t, endTime) - lower(t, startTime);
    }

    long encode(int source, int destination, int timestamp) {
        return ((long) source << 47) | ((long) destination << 30) | timestamp;
    }

    int lower(List<Integer> A, int t) {
        int l = 0, r = A.size();

        while(l < r) {
            int m = (l + r) >>> 1;
            if (A.get(m) < t)
                l = m + 1;
            else
                r = m;
        }

        return l;
    }

    int upper(List<Integer> A, int t) {
        int l = 0, r = A.size();

        while (l < r) {
            int m = (l + r) >>> 1;

            if (A.get(m) <= t)
                l = m + 1;
            else
                r = m;
        }

        return l;
    }

    /**
     * Your Router object will be instantiated and called as such:
     * Router obj = new Router(memoryLimit);
     * boolean param_1 = obj.addPacket(source,destination,timestamp);
     * int[] param_2 = obj.forwardPacket();
     * int param_3 = obj.getCount(destination,startTime,endTime);
     */
    public static void main(String[] args) {
        var router = new Router(4);
        router.addPacket(2, 3, 1);
        router.addPacket(5, 3, 2);
        System.out.println(Collections.singletonList(router.getCount(3, 1, 1)));
        System.out.println(Collections.singletonList(router.getCount(3, 1, 1)));
        router.addPacket(2, 4, 2);
        router.addPacket(4, 3, 2);
        Arrays.stream(router.forwardPacket()).forEach(System.out::print);
    }
}