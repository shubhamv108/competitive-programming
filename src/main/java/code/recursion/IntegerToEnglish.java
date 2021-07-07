package code.recursion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class IntegerToEnglish {

    String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";

        return helper(num).trim();
    }

    String helper(int n) {
        StringBuilder builder = new StringBuilder();
        if (n >= 1000000000) {
            builder.append(helper(n / 1000000000)).append(" Billion ").append(helper(n % 1000000000));
        } else if  (n >= 1000000) {
            builder.append(helper(n / 1000000)).append(" Million ").append(helper(n % 1000000));
        } else if (n >= 1000) {
            builder.append(helper(n / 1000)).append(" Thousand ").append( helper(n % 1000));
        } else if (n >= 100) {
            builder.append(helper(n / 100)).append(" Hundred ").append( helper(n % 100));
        } else if (n >= 20) {
            builder.append(tens[n / 10]).append(" ").append(helper(n % 10));
        } else {
            builder.append(ones[n]);
        }
        return builder.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(new IntegerToEnglish().numberToWords(123456789));
    }

}