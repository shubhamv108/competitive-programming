package code.shubham.java;

/*
    A sealed class or interface can only be extended or implemented by the classes or interfaces
    that are explicitly permitted to do so1. This gives you more control over the design and evolution of your class hierarchies
 */

public sealed class SealedClassExample permits A, B  {
    public static void main(String[] args) {
        new Thread(new Thread()).start();
        var t1 = new Thread();
    }
}

non-sealed class A extends SealedClassExample {

}

final class B extends SealedClassExample {

}
