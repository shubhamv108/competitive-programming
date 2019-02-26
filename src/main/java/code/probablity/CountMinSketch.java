package code.probablity;

import java.util.Arrays;

public class CountMinSketch implements HashFunctions {

    public static void main(String[] args) {
        long countOfHashFunctions = Arrays.asList(HashFunctions.class.getDeclaredMethods()).stream().filter(method -> (method.isDefault() && method.getName().startsWith("h"))).count();
    }

}
