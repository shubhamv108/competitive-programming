package code.shubham.graphs.trees.binaryindextree;

public class DesignMostRecentlyUsedQueue {
    class MRUQueueBIT {

        private class BinaryIndexTree {

            private final int[] tree;
            private final int size;

            public BinaryIndexTree(int size) {
                this.size = size;
                this.tree = new int[this.size + 1];
            }

            public int sum(int index) {
                int result = 0;
                while (index > 0) {
                    result += tree[index];
                    index = parent(index);
                }
                return result;
            }

            public void insert(int index, int value) {
                ++index;
                while (index < size) {
                    tree[index] += value;
                    index = next(index);
                }
            }

            public int parent(int x) {
                return x & (x - 1);
            }

            public int next(int x) {
                return x + (x & -x);
            }
        }

        private final BinaryIndexTree tree;
        private final int[] values;

        private int n;

        public MRUQueueBIT(int n) {
            tree = new BinaryIndexTree(n + 2000);
            this.n = n;
            this.values = new int[n + 2000];
            for (int i = 0; i < n; ++i) {
                this.tree.insert(i, 1);
                this.values[i] = i + 1;
            }
        }

        public int fetch(int k) {
            int l = 0, r = n, pos = -1;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                int sum = this.tree.sum(m);
                if (sum == k)
                    pos = m;
                if (this.tree.sum(m) < k) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }

            this.tree.insert(pos - 1, -1);
            this.tree.insert(n, 1);
            this.values[n] = this.values[l - 1];
            ++this.n;

            return this.values[l - 1];
        }
    }

    static void main() {

    }
}
