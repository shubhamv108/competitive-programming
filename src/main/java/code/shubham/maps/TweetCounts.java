package code.shubham.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

class TweetCounts {

    class Solution {
        private final HashMap<String, ArrayList<Integer>> m = new HashMap<>();

        public void recordTweet(String tweetName, int time) {
            m.computeIfAbsent(tweetName, _ -> new ArrayList<>()).add(time);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            ArrayList<Integer> result = new ArrayList<>();

            int interval = 60;
            if ("hour".equals(freq))
                interval = 3600;
            else if ("day".equals(freq))
                interval = 864000;

            int intervals = (endTime - startTime) / interval;
            IntStream.rangeClosed(0, intervals).forEach(e -> result.add(0));

            ArrayList<Integer> times = m.get(tweetName);
            for (int time : times) {
                if (startTime <= time && time <= endTime) {
                    int idx = (time - startTime) / interval; // <----
                    result.set(idx, result.get(idx) + 1);
                }
            }

            return result;
        }
    }

    class Solution3 {

        class Node {
            int time;
            int count = 0;
            Node l, r;

            Node(int time) {
                this.time = time;
            }

            void insert(int val) {
                if (this.time == val) {
                    ++this.count;
                    return;
                }
                if (val < this.time) {
                    if (l == null)
                        l = new Node(val);
                    l.insert(val);
                } else if (val > this.time) {
                    if (r == null)
                        r = new Node(val);
                    r.insert(val);
                }
            }

            void getCountInRange(int start, int end, ArrayList<Node> timesCount) {
                if (start <= this.time && this.time <= end && this.time != -1)
                    timesCount.add(this);
                if (l != null && start < this.time)
                    l.getCountInRange(start, end, timesCount);
                if (r != null && this.time < end)
                    r.getCountInRange(start, end, timesCount);
            }
        }

        private final HashMap<String, Node> m = new HashMap<>();

        public void recordTweet(String tweetName, int time) {
            m.computeIfAbsent(tweetName, _ -> new Node(time)).insert(time);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            ArrayList<Integer> result = new ArrayList<>();

            int interval = 60;
            if ("hour".equals(freq))
                interval = 3600;
            else if ("day".equals(freq))
                interval = 864000;

            int intervals = (endTime - startTime) / interval;
            IntStream.rangeClosed(0, intervals).forEach(e -> result.add(0));

            ArrayList<Node> timesCount = new ArrayList<>();
            m.getOrDefault(tweetName, new Node(-1)).getCountInRange(startTime, endTime, timesCount);
            for (Node timeCount : timesCount) {
                int idx = (timeCount.time - startTime) / interval; // <----key
                result.set(idx, result.get(idx) + timeCount.count);
            }

            return result;
        }
    }
}

/**
 * Your TweetCounts object will be instantiated and called as such:
 * TweetCounts obj = new TweetCounts();
 * obj.recordTweet(tweetName,time);
 * List<Integer> param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime);
 */