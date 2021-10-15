package code.contestpractice.oa.microsoft;

public class JumpGameIIIReachIndex0ByJumpingLeftOrRightByAOfIndex {
    class Solution {
        public boolean canReach(int[] arr, int start) {
            if (start < 0 || start >= arr.length || arr[start] < 0)
                return false;
            if (arr[start] == 0)
                return true;
            arr[start] = -arr[start];
            return this.canReach(arr, start + arr[start])
                    || this.canReach(arr, start - arr[start]);
        }
    }

}
