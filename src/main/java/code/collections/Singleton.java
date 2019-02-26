package code.collections;

import java.io.Serializable;

public class Singleton implements Cloneable, Serializable {

    public static final long versionUid = 123456789123456789L;

    private static Singleton INSTANCE;

    private Singleton() {}

    public static Singleton getInstance() {
        if (null == INSTANCE) {
            synchronized (Singleton.class) {
                if (null == INSTANCE) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("");
    }

    protected Object readResolve() {
        return INSTANCE;
    }

    protected Object readObject() {
        return INSTANCE;
    }

}

enum SingletonEnum {
    INSTANCE;
}


