package code.stacksqueues;

import java.util.Stack;

public class SimplifyUnixPath {

    public String simplifyPath(String A) {
        var stack = new Stack<String>();
        var builder = new StringBuilder();
        for (int i = 0; i < A.length(); i++) {
            while (i < A.length() && A.charAt(i) == '/') i++;
            while (i < A.length() && A.charAt(i) != '/') builder.append(A.charAt(i++));
            String directoryName = builder.toString();
            builder.setLength(0);
            if (directoryName.length() > 0 && "..".equals(directoryName)) { if (!stack.isEmpty()) { stack.pop(); } continue; }
            if (directoryName.length() > 0 && ".".equals(directoryName)) continue;
            if (directoryName.length() > 0) stack.push(directoryName.toString());
        }
        while (!stack.isEmpty()) {
            builder.insert(0, stack.pop());
            builder.insert(0, '/');
        }
        return builder.length() == 0 ? "/" : builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(
                new SimplifyUnixPath().simplifyPath("/a/./b/../../c/")
        );
    }

}
