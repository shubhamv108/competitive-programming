package code.shubham.priorityqueue;

import java.util.*;

public class KNumbersWithOnlyGivenPrimeFactors {

    List<Integer> firstK(int p1, int p2, int p3, int k)
    {
        Set<Integer> set = new LinkedHashSet<>();
        java.util.PriorityQueue<Integer> pq = new java.util.PriorityQueue();
        pq.offer(p1);
        pq.offer(p2);
        pq.offer(p3);
        while (!pq.isEmpty()) {
            int a = pq.poll();
            if(!set.contains(a)) {
                set.add(a);
                if (set.size() == k) break;
                pq.offer(a * p1);
                pq.offer(a * p2);
                pq.offer(a * p3);
            }
        }
        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int p1 = sc.nextInt();
            int p2 = sc.nextInt();
            int p3 = sc.nextInt();
            int k = sc.nextInt();
            new KNumbersWithOnlyGivenPrimeFactors()
                    .firstK(p1, p2, p3, k).forEach(e -> System.out.printf("%d ", e));
        }
    }
}
