package code.shubham.strings;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;


public class ReplaceSubStringBetweenSpecialChar {

    String replace (String s, char specialChar, Map<String, Object> specialStringValues) {
        StringBuilder result = new StringBuilder();
        StringBuilder specialString = null;
        StringBuilder patternType = null;
        StringBuilder pattern = null;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == specialChar) {
                if (null == specialString) {
                    specialString = new StringBuilder();
                } else {
                    result.append(getSpecialStringValue(specialStringValues, specialString, patternType, pattern));
                    specialString = null;
                    patternType = null;
                    pattern = null;
                }
            } else if (null != specialString) {
                if (c == '=') {
                    patternType = new StringBuilder();
                } else {
                    if (c == '$') {
                        pattern = new StringBuilder();
                    }
                    else if (null != pattern) {
                        pattern.append(c);
                    }
                    else if (null != patternType) {
                        patternType.append(c);
                    }
                    else specialString.append(c);
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private String getSpecialStringValue(Map<String, Object> specialStringValues, StringBuilder specialString, StringBuilder patternType, StringBuilder pattern) {
        String result = String.valueOf(specialStringValues.get(specialString.toString()));
        if (null != result) {
            if (null != patternType) {
                if ("DateTime".equalsIgnoreCase(patternType.toString())) {
                    String p = pattern.toString();
                    if (pattern == null || pattern.length() == 0) p = "hh:mm:ss";
                    result = new SimpleDateFormat(p).format(new Date(Long.valueOf(result)));
                }
            }
        } else {
            result = "";
        }
        return result;
    }

    public static void main(String[] args) {
        Map<String, Object> m = new HashMap<>();
        m.put("WORLD", "Shubham");
        m.put("inquiry", "How are you");
        m.put("valid", System.currentTimeMillis() + 100000);
        System.out.println(
            new ReplaceSubStringBetweenSpecialChar().replace("Hello @WORLD@. @inquiry@? What would you like to do until @valid=DateTime$hh:mm:ss@", '@', m)
        );
    }

}
