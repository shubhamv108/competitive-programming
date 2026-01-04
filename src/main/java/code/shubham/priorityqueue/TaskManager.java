package code.shubham.priorityqueue;

import java.util.List;
import java.util.PriorityQueue;

public class TaskManager {

    int[] P = new int[100001];
    int[] U = new int[100001];

    PriorityQueue<int[]> q = new PriorityQueue<>((x, y) ->
         x[0] == y[0]
                 ? y[1] - x[1]
                 : y[0] - x[0]
    );

    public TaskManager(List<List<Integer>> tasks) {
        tasks.forEach(t -> add(t.get(0), t.get(1), t.get(2)));
    }

    public void add(int userId, int taskId, int priority) {
        U[taskId] = userId;
        edit(taskId, priority);
    }

    public void edit(int taskId, int newPriority) {
        P[taskId] = newPriority;
        q.offer(new int[] { newPriority, taskId });
    }

    public void rmv(int taskId) {
        P[taskId] = -1;
    }

    public int execTop() {
        while (!q.isEmpty() && P[q.peek()[1]] != q.peek()[0])
            q.poll();

        if (q.isEmpty())
            return -1;

        rmv(q.peek()[1]);
        return U[q.poll()[1]];
    }
}
