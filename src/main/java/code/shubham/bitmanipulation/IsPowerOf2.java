package code.shubham.bitmanipulation;

public class IsPowerOf2 {

    boolean is(int n) {
        return (n & (n - 1)) == 0;
    }

}
