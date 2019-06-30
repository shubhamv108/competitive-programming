package code.contestpractice.hackerearth;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class PayPalOne {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            String n = br.readLine();
            printMaxOcurringDigitInPalindrome(n);
            
        }
    }  
    
    private static void printMaxOcurringDigitInPalindrome(String n) {
        int l = n.length();
        Map<Character, Long> map = new HashMap<>();
        for (int i = l - 1; i >= 0; i--) {
            char ch = n.charAt(i);
            if(map.containsKey(ch)) {
            	Long value = map.get(ch);
            	value = value + 1; 
                map.put(ch, value);
            } else {
                map.put(ch, 1L);
            }
        }
        for(Entry<Character, Long> entry : map.entrySet()) {
        	System.out.println(entry.getKey() + " " + entry.getValue());
        }
        char minChar = 'a';
        Long maxOccurrence = -1L;
        Long a = null;
        char c;
        for (Map.Entry<Character, Long> entry : map.entrySet()) {
            a = entry.getValue();
            c = entry.getKey();
            if (a > maxOccurrence) {
                maxOccurrence = a;
                minChar = c;
            } else {
                if(a == maxOccurrence) {
                    if(c < minChar) {
                        minChar = c;
                    }
                }
            }
        }
        System.out.println(maxOccurrence);
        System.out.println(minChar);
        //return sb.toString();
    }
}