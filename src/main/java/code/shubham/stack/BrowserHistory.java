package code.shubham.stack;

import java.util.Stack;

// use Array or ArrayList
public class BrowserHistory {
    private final Stack<String> backward = new Stack<>();
    private final Stack<String> forward = new Stack<>();

    public BrowserHistory(final String homepage) {
        this.backward.push(homepage);
    }

    public void visit(final String url) {
        this.forward.clear();
        this.backward.push(url);
    }

    public String back(int steps) {
        while (!backward.isEmpty() && steps-- > 0)
            forward.push(backward.pop());
        return backward.peek();
    }

    public String forward(int steps) {
        while (!forward.isEmpty() && steps-- > 0)
            backward.push(forward.pop());
        return backward.peek();
    }
}
