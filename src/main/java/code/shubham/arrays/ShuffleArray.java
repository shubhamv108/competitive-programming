package code.shubham.arrays;

import java.util.Random;

public class ShuffleArray {

    private final Random random = new Random();
    private int[] nums;
    private final int[] original;

    public ShuffleArray(int[] nums) {
        this.nums = nums;
        this.original = this.nums.clone();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        this.nums = this.original.clone();
        return this.nums;
    }

    public int[] shuffle() {
        for (int i = 0; i < this.nums.length; i++) {
            this.swap(i, this.randomInRange(i, this.nums.length));
        }
        return this.nums;
    }

    private int randomInRange(int min, int max) {
        return this.random.nextInt(max - min) + min;
    }

    private void swap(int x, int y) {
        int t = this.nums[x];
        this.nums[x] = this.nums[y];
        this.nums[y] = t;
    }
}