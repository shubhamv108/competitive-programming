package code.arithmetic;

public class CountZeroesInEndingInFactorialOfANumber {

    public static int get(int n) {
        int count = 0;

        for (int i = 5; n/i > 0; i *= 5)
            count += n/i;

        return count;
    }

    public static void main(String[] args) {
        int n = 25;
        System.out.println(get(n));
    }

}
