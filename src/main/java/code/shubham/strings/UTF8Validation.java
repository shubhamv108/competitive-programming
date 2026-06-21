package code.shubham.strings;

public class UTF8Validation {
    class Solution {
        public boolean validUtf8(int[] A) {
            int n = A.length;

            // Number of bytes in the current UTF-8 character
            int numberOfBytesToProcess = 0;

            // Masks to check two most significant bits in a byte.
            int mask1 = 1 << 7;
            int mask2 = 1 << 6;

            // For each integer in the data array.
            for (int i = 0; i < n; ++i) {
                // If this is the case then we are to start processing a new UTF-8 character.
                if (numberOfBytesToProcess == 0) {
                    int mask = 1 << 7;
                    while ((mask & A[i]) != 0) {
                        ++numberOfBytesToProcess;
                        mask = mask >> 1;
                    }

                    // 1 byte characters
                    if (numberOfBytesToProcess == 0)
                        continue;

                    // Invalid scenarios according to the rules of the problem.
                    if (numberOfBytesToProcess > 4 || numberOfBytesToProcess == 1)
                        return false;
                } else {

                    // data[i] should have most significant bit set and
                    // second most significant bit unset. So, we use the two masks
                    // to make sure this is the case.
                    if (!((A[i] & mask1) != 0 && (A[i] & mask2) == 0))
                        return false;
                }

                // We reduce the number of bytes to process by 1 after each integer.
                --numberOfBytesToProcess;
            }

            // This is for the case where we might not have the complete data for
            // a particular UTF-8 character.
            return numberOfBytesToProcess == 0;
        }
    }

    void main() {
        System.out.println(new Solution().validUtf8(new int[] { 197, 130, 1 }));
    }
}
