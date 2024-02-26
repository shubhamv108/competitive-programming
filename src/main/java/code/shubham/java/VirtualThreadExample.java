package code.shubham.java;

public class VirtualThreadExample {
    public static void main(String[] args) {
        Thread.ofVirtual().start(() -> System.out.println());
        Thread.startVirtualThread(() -> System.out.println());
    }
}
