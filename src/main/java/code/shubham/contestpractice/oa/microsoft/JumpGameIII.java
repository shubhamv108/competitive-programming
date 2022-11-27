package code.shubham.contestpractice.oa.microsoft;

public class JumpGameIII {
    class Solution {
        public boolean canReach(int[] A, int index) {
            if (index < 0 || index >= A.length || A[index] < 0)
                return false;

            if (A[index] == 0)
                return true;

            A[index] = -A[index];
            return canReach(A, index - A[index]) || canReach(A, index + A[index]);
        }
    }

}
