package code.shubham.arrays.mex;

import input.InputUtils;

public class SmallestMissingPositiveNumber {

    public int solution(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] <= 0 || A[i] >= A.length) continue;
            int cur = A[i], point;
            while (cur > 0 && cur <= A.length && A[cur - 1] != cur) {
                point = A[cur - 1];
                A[cur - 1] = cur;
                cur = point;
                if (cur < 0 || cur >= A.length) break;
            }
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] != i+1) return i+1;
        }
        return A.length + 1;
    }

    public static void main(String[] args) {
        System.out.println(
                new SmallestMissingPositiveNumber().solution(InputUtils.nextIntLine())
        );
    }

}
