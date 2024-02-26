package code.shubham.java;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {


    public CustomClassLoader(final ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("Loading Class '" + name + "'");
        if (name.startsWith("code.shubham")) {
            System.out.println("Loading Class using CustomClassLoader");
            return getClass(name);
        }
        return super.loadClass(name);
    }

    private Class getClass(final String name) throws ClassNotFoundException {
        String file = name.replace('.', File.separatorChar) + ".class";
        byte[] b = null;
        try {
            // This loads the byte code data from the file
            b = loadClassFileData(file);
            // defineClass is inherited from the ClassLoader class
            // that converts byte array into a Class. defineClass is Final
            // so we cannot override it
            final Class c = defineClass(name, b, 0, b.length);
            resolveClass(c);
            return c;
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] loadClassFileData(String name) throws IOException {
        final InputStream stream = getClass().getClassLoader().getResourceAsStream(
                name);
        final int size = stream.available();
        final byte buff[] = new byte[size];
        final DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }
}
