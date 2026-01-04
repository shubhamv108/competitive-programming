package code.shubham.utils;

public class Pair<Key extends Object, Value extends  Object> {
    public final Key k;
    public final Value v;

    public Pair(Key k, Value v) {
        this.k = k;
        this.v = v;
    }

    public Key k() {
        return k;
    }

    public Value v() {
        return v;
    }
}
