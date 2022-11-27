package code.shubham.contestpractice.hackerearth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    /*
        You can declare the other variables or functions here
    */
    private static final String musicRegex = "\\w*[a-zA-Z]\\w*\\s[1-9][0-9]*$";
    private static Pattern musicPattern = Pattern.compile(musicRegex);
    private static final String videoRegex = "\\w*[a-zA-Z]\\w*\\s[1-9][0-9]*\\s[1-9][0-9]*$";
    private static Pattern videoPattern = Pattern.compile(videoRegex);
    private static final String nameRegex  = "\\w*[a-zA-Z]\\w*";
    private static Pattern namePattern = Pattern.compile(nameRegex);
    private static final String integerRegex = "^[1-9][0-9]*$";
    private static Pattern integerPattern = Pattern.compile(integerRegex);
    public static void process_data(String a){
        Matcher m1 = musicPattern.matcher(a);
        while(m1.find()) {
            System.out.println("M");
            return;
        }
        m1 = videoPattern.matcher(a);
        while(m1.find()) {
            System.out.println("V");
            return;
        }
        System.out.println("N");

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = n;
        while (m-- > 0) {
            process_data(br.readLine());
        }
    }
}
