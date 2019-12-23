package code.datastructures;

class StackNode<T> {
    T t;
    StackNode<T> prev;
    StackNode(T t) {
        this.t = t;
    }
}

public class Stack<T> {

    StackNode<T> top;

    public void push(T t) {
        StackNode<T> n = new StackNode<T>(t);
        n.prev = top;
        top = n;
    }

    public T pop() {
        T t = top.t;
        top = top.prev;
        return t;
    }

    boolean isEmpty() {
        return top == null;
    }

}
