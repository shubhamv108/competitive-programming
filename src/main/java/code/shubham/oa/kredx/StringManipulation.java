package code.shubham.oa.kredx;

public class StringManipulation {
    public static void main(String[] args) {
        StringBuilder s1 = new StringBuilder("1");
        StringBuilder s2 = new StringBuilder(1);
        String s3 = s1.substring(0);
        char s4 = s1.charAt(0);
        String s5 = s3.concat(s4 + "");
        String s6 = s5.concat(s3).replace("1", "2");
        System.out.println(s6);
    }
}
