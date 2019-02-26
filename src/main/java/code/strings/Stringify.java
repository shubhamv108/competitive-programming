package code.strings;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Stringify {
    private String stringify(boolean b) {
        return "" + b;
    }

    private String stringify(byte b) {
        return ""; /* ToDo */
    }

    private String stringify(char c)   {
        return "" + c;
    }

    private String stringify(int i)  {
        return "" + i;
    }

    private String stringify(long l) {
        return "" + l;
    }

    private String stringify(float f) { return "" + f; }

    private String stringify(double d) {
        return "" + d;
    }

    public String stringify(Object o) {
        StringBuilder sb = new StringBuilder();
        if (Objects.isNull(o))
            return "";

        if (o instanceof Boolean)
            return o.toString();
        if (o instanceof Byte)
            /*ToDo*/
        if (o instanceof Void)
            /*ToDo*/
        if (o instanceof Character)
            return o.toString();
        if (o instanceof String)
            return o.toString();
        if (o instanceof Integer)
            return o.toString();
        if (o instanceof Long)
            return o.toString();
        if (o instanceof Float)
            return o.toString();
        if (o instanceof Double)
            return o.toString();
        if (o instanceof boolean[]) {
            boolean[] a = (boolean[]) o;
            for (int i=0;i<a.length;i++)
                sb.append(i+1).
                        append(")").
                        append(" ").
                        append(stringify(a[i])).
                        append(";").
                        append(" ");
            return sb.toString();
        }
        if (o instanceof byte[]) {
            /*ToDo*/
        }
        if (o instanceof char[]) {
            char[] a = (char[]) o;
            for (int i=0; i<a.length;i++)
                sb.append(i+1).
                        append(")").
                        append(" ").
                        append(stringify(a[i])).
                        append(";").
                        append(" ");
            return sb.toString();
        }
        if (o instanceof int[]) {
            int[] a = (int[]) o;
            for (int i=0;i<a.length;i++)
                sb.append(i+1).
                        append(")").
                        append(" ").
                        append(stringify(a[i])).
                        append(";").
                        append(" ");
            return sb.toString();
        }
        if (o instanceof long[]) {
            long[] a = (long[]) o;
            for (int i=0;i<a.length;i++)
                sb.append(i+1).
                        append(")").
                        append(" ").
                        append(stringify(a[i])).
                        append(";").
                        append(" ");
            return sb.toString();
        }
        if (o instanceof float[]) {
            float[] a = (float[]) o;
            for (int i=0;i<a.length;i++)
                sb.append(i+1).
                        append(")").
                        append(" ").
                        append(stringify(a[i])).
                        append(";").
                        append(" ");
            return sb.toString();
        }
        if (o instanceof double[]) {
            double[] a = (double[]) o;
            for (int i=0;i<a.length;i++)
                sb.append(i+1).
                        append(")").
                        append(" ").
                        append(stringify(a[i])).
                        append(";").
                        append(" ");
            return sb.toString();
        }
        if (o instanceof List) {
            List a = (List) o;
            a.stream().map(e -> sb.
                    append(")").
                    append(" ").
                    append(stringify(e)).
                    append(";").
                    append(" "));
        }
        if (o instanceof Map) {

        }
        if (o instanceof Set) {

        }
        return o.toString();
    }

    public static void main(String[] args) {
        boolean[] b = {true, false};
        System.out.println(new Stringify().stringify(b));
    }
}
