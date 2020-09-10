package code.math;

public class SmallestNumberGreaterWithEqualSumOfDigits {

    int solution(int n) {
        Long sum = sum(n);

        String stringN = String.valueOf(n);
        int size = stringN.length();
        if (n < 0) size -= 1;

        for (int i = size - 1; ; i--) {

        }

    }

    Long sum(int n) {
        if (n < 0) n *= -1;
        Long sum = 0L;
        while (n > 0) {
            sum += (n % 10);
            n /= 10;
        }
        return sum;
    }

}
