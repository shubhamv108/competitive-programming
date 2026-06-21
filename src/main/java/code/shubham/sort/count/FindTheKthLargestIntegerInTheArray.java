package code.shubham.sort.count;

import java.math.BigInteger;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class FindTheKthLargestIntegerInTheArray {
    void main() {
        System.out.println(new Solution2().kthLargestNumber(new String[]{"3", "6", "7", "10"}, 4));
    }

    class Solution {
        public String kthLargestNumber(String[] A, int k) {
            TreeMap<BigInteger, Integer> m = new TreeMap<>();
            for (String a : A) {
                BigInteger n = new BigInteger(a);
                m.put(n, m.getOrDefault(n, 0) + 1);
            }

            for (Map.Entry<BigInteger, Integer> e : m.entrySet()) {
                k -= e.getValue();
                if (k <= 0)
                    return e.getKey().toString();
            }

            return "";
        }
    }

    class Solution2 {
        public String kthLargestNumber(String[] A, int k) {
            TreeMap<String, Integer> m = new TreeMap<>((x, y) ->
                                                               x.length() == y.length() ? y.compareTo(x) : y.length() - x.length());

            for (String a : A)
                m.put(a, m.getOrDefault(a, 0) + 1);

            for (Map.Entry<String, Integer> e : m.reversed().entrySet()) {
                k -= e.getValue();
                if (k <= 0)
                    return e.getKey().toString();
            }

            return "";
        }
    }

    class Solution3 {
        private static Random random = new Random();

        public String kthLargestNumber(String[] nums, int k) {
            return quickSelect(nums, 0, nums.length - 1, nums.length - k);
        }

        public String quickSelect(String[] A, int l, int r, int t) {
            while (l <= r) {
                int pivotIndex = l + random.nextInt(r - l + 1);
                String pivotValue = A[pivotIndex];
                swap(A, pivotIndex, r);
                int lt = l;
                int gt = r;
                int i = l;

                while (i <= gt) {
                    int comp = compare(A[i], pivotValue);
                    if (comp < 0) swap(A, i++, lt++);
                    else if (comp > 0) swap(A, i, gt--);
                    else i++;
                }

                if (t < lt) r = lt - 1;
                else if (t > gt) l = gt + 1;
                else return A[t];
            }

            return A[t];
        }

        public void swap(String[] A, int a, int b) {
            String t = A[a];
            A[a] = A[b];
            A[b] = t;
        }

        public int compare(String x, String y) {
            return x.length() == y.length() ? y.compareTo(x) : y.length() - x.length();
        }
    }
}
