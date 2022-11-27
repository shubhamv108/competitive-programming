package code.shubham.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class PrintAllSubsequencesOfString {

    class SolutionRecursive {
        List<String> result = new ArrayList<>();
        List<String> getAllSubsequences(StringBuilder s, StringBuilder subsequence) {
            if (0 == s.length()) {
                result.add(subsequence.toString());
                return result;
            }
            char c = s.charAt(0);
            s = s.deleteCharAt(0);
            getAllSubsequences(s, subsequence);
            getAllSubsequences(s, subsequence.append(c));
            s.insert(0, c);
            subsequence.deleteCharAt(subsequence.length() - 1);
            return result;
        }
    }

    class SolutionArray {
        List<String> result = new ArrayList<>();
        List<String> getAllSubsequences(char[] s, int index, StringBuilder subsequence) {
            if (index == s.length) {
                result.add(subsequence.toString());
                return result;
            }
            getAllSubsequences(s, index + 1, subsequence);
            getAllSubsequences(s, index + 1, subsequence.append(s[index]));
            subsequence.deleteCharAt(subsequence.length() - 1);
            return result;
        }
    }

    class SolutionDP {
        char[] c;
        int l;
        List<String>[] dp;
        SolutionDP(String s) {
            this.c = s.toCharArray();
            this.l = c.length;
            this.dp = new ArrayList[this.l];
            IntStream.range(0, l).forEach(i -> this.dp[i] = new ArrayList<>());
        }
        List<String> getAllSubsequences(int index, StringBuilder subsequence) {
            if (index == this.l) return Arrays.asList(subsequence.toString());
            List<String> result = new ArrayList<>();
            if (!dp[index].isEmpty()) {
                for (String t : dp[index]) {
                    result.add(subsequence.toString() + t);
                }
                return result;
            }
            result.addAll(getAllSubsequences(index + 1, subsequence));
            result.addAll(getAllSubsequences(index + 1, subsequence.append(this.c[index])));
            subsequence.deleteCharAt(subsequence.length() - 1);
            return dp[index] = result;
        }

        void printDpArray() {
            IntStream.range(0, this.l).mapToObj(i -> dp[i]).forEach(System.out::print);
        }
    }

    class SolutionDP2 {
        char[] c;
        int l;
        List<String>[] dp;
        SolutionDP2(String s) {
            this.c = s.toCharArray();
            this.l = c.length;
            this.dp = new ArrayList[this.l];
            IntStream.range(0, l).forEach(i -> this.dp[i] = new ArrayList<>());
        }
        List<String> getAllSubsequences() {
            this.dp[l-1].addAll(Arrays.asList("", c[l-1] + ""));
            for (int i = this.l-2; i > -1; i--) {
                for (String s : dp[i+1]) {
                    dp[i].add(s);
                    dp[i].add(c[i] + s);
                }
            }
            return dp[0];
        }

        void printDpArray() {
            IntStream.range(0, this.l).mapToObj(i -> dp[i]).forEach(System.out::print);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new PrintAllSubsequencesOfString().new SolutionArray().getAllSubsequences("abcd".toCharArray(), 0, new StringBuilder())
        );
        SolutionDP solution = new PrintAllSubsequencesOfString().new SolutionDP("abcd");
        System.out.println(
                solution.getAllSubsequences(0, new StringBuilder())
        );
        solution.printDpArray();
        System.out.println();
        SolutionDP2 solutionDP2 = new PrintAllSubsequencesOfString().new SolutionDP2("abcd");
        System.out.println(
                solutionDP2.getAllSubsequences()
        );
        solutionDP2.printDpArray();
    }

}
