package code.shubham.java;

public class shortexample {
    class A {
        int add(int a, int b) {
            return a + b;
        }
    }

    public static void main(String[] args) {
        short s = 9;
        System.out.println(new shortexample().new A().add(s, 6));
    }

}
