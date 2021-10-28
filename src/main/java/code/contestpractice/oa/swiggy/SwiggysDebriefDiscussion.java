package code.contestpractice.oa.swiggy;

public class SwiggysDebriefDiscussion {

    class Solution {
        int solve(int n) {
            int g = n / 2;
            return (permutations(g) * 2) - 1;
        }

        int permutations(int n) {
            return factorial(n);
        }

        int factorial(int n) {
            int result = 1;
            while (n > 1)
                result *= n--;
            return result;
        }
    }

    public static void main(String[] args) {
        SwiggysDebriefDiscussion discussion = new SwiggysDebriefDiscussion();
        Solution solution = discussion.new Solution();
        System.out.println(solution.solve(4));
    }

}
