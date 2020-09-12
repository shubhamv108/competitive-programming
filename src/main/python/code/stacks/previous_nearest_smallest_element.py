class Solution:
    def prevSmaller(self, A):
        result = [-1]
        stack = [A[0]]
        for i in range(1, len(A)):
            while len(stack) > 0 and stack[-1] >= A[i]:
                stack.pop()
            if len(stack) > 0:
                result.append(stack[-1])
            else:
                result.append(-1)
            stack.append(A[i])
        return result
