package code.shubham.bitmanipulation;

public class ToogleLastSetBit {

    int toogle(int n) {
        return n & (n-1);
    }

}
