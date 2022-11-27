package code.shubham.backtracking.booleananswer;

class Solver {

    boolean hasEqualSumPartitionsHelper(int[] arr) {
        return false;
    }

    boolean hasEqualSumPartitions(int[] arr) {
        return hasEqualSumPartitionsHelper(arr);
    }

    public boolean solve(int[] arr) {
        return hasEqualSumPartitions(arr);

    }
}

public class HasEqualSumPartitions {
    public static void main(String[] args) {
        System.out.println(new Solver().solve(new int[] {5, 7, 2, 4}));
    }
}