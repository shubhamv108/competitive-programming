package code.trees.segmenttrees;

public class RangeSumQueryImmutable {

    class NumArray {
        int[] nums;
        public NumArray(int[] nums) {
            for(int i = 1; i < nums.length; i++) nums[i] += nums[i - 1];
            this.nums = nums;
        }
        public int sumRange(int i, int j) {
            if(i == 0) return nums[j];
            return nums[j] - nums[i - 1];
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new RangeSumQueryImmutable().new NumArray(new int[]{-2, 0, 3, -5, 2, -1}).sumRange(0, 2)
        );
    }

}
