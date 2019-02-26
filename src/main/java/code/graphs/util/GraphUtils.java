package code.graphs.util;

import code.graphs.Edge;

import java.util.List;
import java.util.Map;

public class GraphUtils {

    public static int[][] mapTo2DArray(Map<Integer, List<Edge>> m) {
        int S = m.size();
        int[][] arr = new int[S][S];
        m.entrySet().stream().forEach(i -> {
            i.getValue().stream().forEach(e -> arr[i.getKey()][e.v] = e.cost);
        });
        return arr;
    }

}
