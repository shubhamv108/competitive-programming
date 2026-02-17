package code.shubham.bitmanipulation;

public class IsIthBitSet {

    boolean is(int n, int i) {
        return (n & (1 << i)) != 0;
    }

    public static void main(String[] args) {
        System.out.println(new IsIthBitSet().is(13, 2));
    }

    // 1 1 0 1
    // 0 1 0 0
    // -------

}
