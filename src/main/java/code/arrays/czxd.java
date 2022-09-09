package code.arrays;

public class czxd {
    public static void main(String[] args) {
        A a = new B();
        a.a();
    }

    static class A {
        static void a() {
            System.out.println("a");
        }
    }

    static class B extends A {
        static void a() {
            System.out.println("b");
        }
    }
}
