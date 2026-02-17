package code.shubham.bitmanipulation;

public class SetIthBit {

    int set(int n, int i) {
        return n | (1 << i);
    }

    public static void main(String[] args) {
        System.out.println(new SetIthBit().set(13, 2));
    }

}
