package code.shubham.arrays;

public class BrowserHistory {

    private String[] history = new String[50000];
    private int cur = -1, max = -1;

    public BrowserHistory(String homepage) {
        visit(homepage);
    }

    public void visit(String url) {
        this.history[++cur] = url;
        max = cur;
    }

    public String back(int steps) {
        cur = Math.max(cur - steps, 0);
        return history[cur];
    }

    public String forward(int steps) {
        cur = Math.min(cur + steps, max);
        return history[cur];
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
