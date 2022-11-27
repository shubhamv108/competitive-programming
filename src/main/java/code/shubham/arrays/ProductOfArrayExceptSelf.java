package code.shubham.arrays;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = nums[i-1] * result[i-1];
        }

        int r = 1;
        for (int i = nums.length - 1; i > -1; i--) {
            result[i] = result[i] * r;
            r *= nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        Arrays.stream(new ProductOfArrayExceptSelf().productExceptSelf(new int[] {1, 2, 3, 4})).forEach(e -> System.out.print(e + " "));
    }

}
