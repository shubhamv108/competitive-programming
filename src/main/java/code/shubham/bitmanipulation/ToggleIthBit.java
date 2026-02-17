package code.shubham.bitmanipulation;

public class ToggleIthBit {
    int toggle(int n, int i) {
        return n ^ (1 << i);
    }

    public static void main(String[] args) {
        System.out.println(new ToggleIthBit().toggle(13, 2));
    }
}
