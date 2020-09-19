
class Solution:

    def main():
        T = int(input())
        for _ in range(0, T):
            S = input()
            signs = [c for c in S]
            cur_smallest = 1
            weights= []
            for _ in range(0, len(S) + 1): weights.append(0)
            i = 0
            while i < len(S):
                count = 0
                for j in range(i, len(S)):
                    if signs[j] == '<': break
                    count += 1

                x = cur_smallest + count
                cur_smallest = x + 1
                for j in range(i, i + count + 1):
                    weights[j] = x
                    x = x - 1

                i = i + count + 1

            if weights[-1] == 0 and signs[-1] == '<':
                weights[-1] = cur_smallest

            result = ""
            for i in range(0, len(weights)):
                result += chr(weights[i] + 96)

            print(result)


    if __name__ == "__main__" :
        main()