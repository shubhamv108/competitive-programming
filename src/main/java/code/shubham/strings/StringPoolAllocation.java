package code.shubham.strings;

public class StringPoolAllocation {
    public static void main(String[] args) {
        String str = "hello there!";
        String str1 = "hello" + " " + "there!";
        String str2 = "hello" + " " + "there" + "!";
        String str3 = "hello" + " " + "there" + "!" + "!";
    }
}
