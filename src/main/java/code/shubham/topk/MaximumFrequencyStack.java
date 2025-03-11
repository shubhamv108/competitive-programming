package code.shubham.topk;

import java.util.ArrayList;
import java.util.HashMap;

public class MaximumFrequencyStack {
    class FreqStack {
        HashMap<Integer, Integer> freq;
        ArrayList<ArrayList<Integer>> bucket;
        int maxfreq;

        public FreqStack() {
            this.freq = new HashMap<>();
            this.bucket = new ArrayList<>();
            bucket.add(new ArrayList<>());
            this.maxfreq = 0;
        }

        public void push(int val) {
            int currfreq = freq.getOrDefault(val, 0) + 1;
            freq.put(val, currfreq);
            if (bucket.size() == currfreq) {
                bucket.add(new ArrayList<>());
            }
            bucket.get(currfreq).add(val);
            maxfreq = Math.max(maxfreq, currfreq);
        }

        public int pop() {
            if (maxfreq == 0) {
                return -1;
            }
            int NoOfMaxfreqEle = bucket.get(maxfreq).size();
            int maxfreqele = bucket.get(maxfreq).get(NoOfMaxfreqEle-1);
            freq.put(maxfreqele,maxfreq-1);
            bucket.get(maxfreq).remove(NoOfMaxfreqEle-1);
            if(NoOfMaxfreqEle == 1){
                maxfreq -= 1;
            }
            return maxfreqele;
        }
    }

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */
}
