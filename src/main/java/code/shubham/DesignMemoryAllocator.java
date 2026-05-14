package code.shubham;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class DesignMemoryAllocator {
    class Allocator {
        int capacity = 0;
        HashMap<Integer, List<Integer>> startOffsets;
        TreeMap<Integer, Integer> memory;

        public Allocator(int n) {
            capacity = n;
            startOffsets = new HashMap<>();
            memory = new TreeMap<>();
        }

        public int allocate(int size, int mID) {
            Integer ite = memory.isEmpty() ? null : memory.firstKey();
            Integer start = 0;
            if (ite == null || ite >= size) {
                if (size > capacity)
                    return -1;

                start = 0;
                memory.put(start, size);
                startOffsets.computeIfAbsent(mID, a -> new ArrayList<>()).add(start);
                return start;
            }

            Integer nextStart = memory.higherKey(ite);
            while (nextStart != null && nextStart - memory.get(ite) < size) {
                ite = nextStart;
                nextStart = memory.higherKey(ite);
            }

            if (nextStart != null || capacity - memory.get(ite) >= size) {
                start = memory.get(ite);
                memory.put(start, start + size);
                startOffsets.computeIfAbsent(mID, a -> new ArrayList<>()).add(start);
                return start;
            } else
                return -1;
        }

        public int free(int mID) {
            List<Integer> starts = startOffsets.get(mID);
            if (starts == null || starts.isEmpty())
                return 0;

            int ret = 0;
            for (int start: starts) {
                Integer end = memory.get(start);
                ret += (end-start);
                memory.remove(start);
            }
            startOffsets.remove(mID);
            return ret;
        }

    }

    class Allocator2 {
        private int[] memory;
        public Allocator2(int n) {
            memory = new int[n];
        }

        public int allocate(int size, int mID) {
            int consecutive = 0;
            for (int i = 0; i < memory.length; ++i) {
                if (memory[i] == 0) {
                    ++consecutive;
                    if (consecutive == size){
                        int st = i - size + 1;
                        for (int j = st; j <= i; ++j)
                            memory[j] = mID;
                        return st;
                    }
                } else
                    consecutive = 0;
            }
            return -1;
        }

        public int freeMemory(int mID) {
            int count = 0;
            for (int i = 0; i < memory.length; ++i) {
                if(memory[i] == mID) {
                    memory[i] = 0;
                    ++count;
                }
            }
            return count;
        }
    }

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator obj = new Allocator(n);
 * int param_1 = obj.allocate(size,mID);
 * int param_2 = obj.freeMemory(mID);
 */

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator obj = new Allocator(n);
 * int param_1 = obj.allocate(size,mID);
 * int param_2 = obj.freeMemory(mID);
 */
}
