package code.shubham.java;

import java.lang.reflect.Method;

public class CustomClassLoaderDriver {
    public static void main(String args[]) throws Exception {
        String progClass = args[0];
        String progArgs[] = new String[args.length - 1];
        System.arraycopy(args, 1, progArgs, 0, progArgs.length);

        final CustomClassLoader ccl = new CustomClassLoader(CustomClassLoader.class.getClassLoader());
        final Class clas = ccl.loadClass(progClass);
        final Class mainArgType[] = { (new String[0]).getClass() };
        final Method main = clas.getMethod("main", mainArgType);
        final Object argsArray[] = { progArgs };
        main.invoke(null, argsArray);

        // Below method is used to check that the Foo is getting loaded
        // by our custom class loader i.e CCLoader
        final Method printCL = clas.getMethod("printCL", null);
        printCL.invoke(null, new Object[0]);
    }
}
