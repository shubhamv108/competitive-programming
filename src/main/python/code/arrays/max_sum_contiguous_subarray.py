class Solution:
    # @param A : tuple of integers
    # @return an integer
    def maxSubArray(self, A):
        sum = A[0]
        largestSum = A[0]
        for i in range(1, len(A)):
            sum = max(A[i], sum + A[i])
            largestSum = max(largestSum, sum)
        return largestSum
