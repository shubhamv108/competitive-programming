package code.shubham.unionfind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class EvaluateDivision {

    class Solution {
        public double[] calcEquation(List<List<String>> E, double[] V, List<List<String>> Q) {
            double[] result = new double[Q.size()];
            UF uf = new UF();
            IntStream.range(0, E.size())
                    .forEach(i -> uf.union(E.get(i).get(0), E.get(i).get(1), V[i]));

            IntStream.range(0, Q.size()).forEach(i -> {
                if (!uf.isPresent(Q.get(i).get(0)) || !uf.isPresent(Q.get(i).get(1))) {
                    result[i] = -1;
                } else {
                    P dvd = uf.find(Q.get(i).get(0));
                    P dvr = uf.find(Q.get(i).get(1));

                    if (!dvd.n.equals(dvr.n))
                        result[i] = -1.0;
                    else
                        result[i] = dvd.v / dvr.v;
                }
            });

            return result;
        }

        class P {
            String n;
            double v;
            P (String n, double v) {
                this.n = n;
                this.v = v;
            }
        }

        class UF {
            private final Map<String, P> m = new HashMap<>();

            P find(String n) {
                P p = m.computeIfAbsent(n, _ -> new P(n, 1.0));
                if (!n.equals(p.n)) {
                    P e = find(p.n);
                    m.put(n, new P(e.n, p.v * e.v));
                }
                return m.get(n);
            }

            private void union(String dividend, String divisor, Double value) {
                P dvd = find(dividend);
                P dvr = find(divisor);

                if (!dvd.n.equals(dvr.n))
                    m.put(dvd.n, new P(
                            dvr.n,
                            dvr.v * value / dvd.v));

            }

            boolean isPresent(String n) {
                return m.containsKey(n);
            }

        }
    }

    public static void main(String[] args) {
        Arrays.stream(new EvaluateDivision().new Solution()
                .calcEquation(
                    Arrays.asList(
                            Arrays.asList("a", "b"),
                            Arrays.asList("b", "c")),
                    new double[] {
                            2.0,
                            3.0
                    },
                    Arrays.asList(
                            Arrays.asList("a", "c"),
                            Arrays.asList("b", "a"),
                            Arrays.asList("a", "e"),
                            Arrays.asList("a", "a"),
                            Arrays.asList("x", "x")
                    )
                )).forEach(System.out::println);
    }

}
