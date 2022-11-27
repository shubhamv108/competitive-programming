package code.shubham.collections;

public class UncheckedException extends RuntimeException {

    public UncheckedException(String msg, Throwable e) {
        super(msg, e);
    }

}
