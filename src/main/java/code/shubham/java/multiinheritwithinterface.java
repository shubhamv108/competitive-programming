package code.shubham.java;

public class multiinheritwithinterface {
    interface I {
        void method();
    }

    class C {
        public void method() {
            System.out.println("C::method");
        }
    }

    class implC extends C implements I {

    }

    public static void main(String[] args) {
        new multiinheritwithinterface().new implC().method();
    }
}
