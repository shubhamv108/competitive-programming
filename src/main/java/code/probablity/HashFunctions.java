package code.probablity;

public interface HashFunctions {
    default int h1(String key) {
        return 0;
    }

    default int h2(String key) {
        return 1;
    }

    default int h3(String key) {
        return 2;
    }

    default int h4(String key) {
        return 3;
    }
}
