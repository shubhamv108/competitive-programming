class Solution:
    # @param A : tuple of integers
    # @param B : integer
    # @return a list of integers
    def slidingMaximum(self, A, B):
        result = []
        queue = []
        for i in range(0, B):
            while len(queue) > 0 and A[queue[-1]] < A[i]: queue.pop()
            queue.append(i)

        for i in range(B, len(A)):
            result.append(A[queue[0]])
            while len(queue) > 0 and queue[0] <= i - B: queue.pop(0)
            while len(queue) > 0 and A[queue[-1]] < A[i]: queue.pop()
            queue.append(i)
        result.append(A[queue[0]])

        return result


