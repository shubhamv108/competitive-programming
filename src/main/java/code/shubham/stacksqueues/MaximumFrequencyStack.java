package code.shubham.stacksqueues;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MaximumFrequencyStack {

    private Map<Integer, Integer> frequencies = new HashMap<>();
    private Map<Integer, Stack<Integer>> maxFrequencies = new HashMap<>();
    private int maxFrequency;

    public void push(int x) {
        int freq = this.frequencies.getOrDefault(x, 0) + 1;
        this.frequencies.put(x, freq);
        this.maxFrequency = Math.max(freq, maxFrequency);
        this.maxFrequencies.computeIfAbsent(freq, f -> new Stack()).push(x);
    }

    public int pop() {
        int result = this.maxFrequencies.get(this.maxFrequency).pop();
        if (this.maxFrequencies.get(this.maxFrequency).isEmpty()) {
            this.maxFrequency--;
        }
        this.frequencies.put(result, this.frequencies.get(result) - 1);
        return result;
    }
}