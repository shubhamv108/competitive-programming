package code.shubham.java;

public class Test {
    int getSumVal(int num) {
        if (num <= 0)
            return 0;
        return getSumVal(num--);

    }
    public static void main(String[] args) {
        System.out.println(new Test().getSumVal(10));
    }
}