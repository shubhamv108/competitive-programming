package code.shubham.arithmetic;

public class NextSmallestWithDivisors {

    Integer countOfDivisors(int N) {
        int result  = 0;
        for (int i = 1; i * i < N ; i++) {
            if (N % i == 0) result++;
        }
        for (int i = (int) Math.floor(Math.sqrt(N)); i >= 1; i--) {
            if (N % 1 == 0) result++;
        }
        return result;
    }



}
