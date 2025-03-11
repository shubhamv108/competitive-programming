package code.shubham.binarysearch;

public class SearchInASortedInfiniteArray {

     interface ArrayReader {
        public int get(int index);
    }

    class Reader implements ArrayReader {

         int[] A;

         Reader(int[] A) {
             this.A = A;
         }

        @Override
        public int get(int index) {
            return A[index];
        }
    }

    class Solution {
        public int search(ArrayReader reader, int t) {
            if (reader.get(0) == t)
                return 0;

            int l = 0, r = 1;
            while (reader.get(r) < t) {
                l = r;
                r <<= 1;
            }

            while (l <= r) {
                int m = l + ((r - l) >> 1);
                int a = reader.get(m);
                if (a == t)
                    return m;
                else if (a > t)
                    r = m - 1;
                else
                    l = m + 1;
            }

            return -1;
        }
    }

    class Solution2 {
        public int search(ArrayReader reader, int t) {
            int l = 0, r = Integer.MAX_VALUE, m;
            while (l <= r) {
                m = l + ((r - l) >> 1);
                int a = reader.get(m);
                if (a == t)
                    return m;
                else if (a > t)
                    r = m - 1;
                else
                    l = m + 1;
            }

            return -1;
        }
    }

}
