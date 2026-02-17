package code.shubham.bitmanipulation;

public class ClearIthBit {

    int clear(int n, int i) {
        return n & (~(1 << i));
    }

    public static void main(String[] args) {
        System.out.println(new ClearIthBit().clear(13, 2));
    }

}
