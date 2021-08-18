package code.arithmetic;

public class IsPairCoPrime {

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    boolean get(int a, int b) {
        return this.gcd(a, b) == 1;
    }

    public static void main(String[] args) {
        System.out.println(
                new IsPairCoPrime().get(4, 7)
        );
    }

}
