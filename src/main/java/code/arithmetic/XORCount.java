package code.arithmetic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class XORCount {

    Map<Integer, Integer> xorCount = new HashMap<>();
    List<Integer> solve(int N, int[] A, int K) {
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j< N; j++) {
                int xor = A[i] ^ A[j];
                if (xor <= K) {
                    xorCount.merge(xor, 1, Integer::sum);
                }
            }
        }
        return IntStream.range(0, K+1)
                .mapToObj(i -> xorCount.getOrDefault(i, 0))
                .collect(Collectors.toList());
    }

}
