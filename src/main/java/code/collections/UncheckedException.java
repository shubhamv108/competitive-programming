package code.collections;

public class UncheckedException extends RuntimeException {

    public UncheckedException(String msg, Throwable e) {
        super(msg, e);
    }

}
