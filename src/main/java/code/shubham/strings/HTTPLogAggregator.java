package code.shubham.strings;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Input:
 * #88.191.254.20 - - [22/Mar/2009:07:00:32 +0100] "GET / HTTP/1.0" 501 8674 "-" "-" "-"
 * #66.249.66.231 - - [22/Mar/2009:07:06:20 +0100] "GET /popup.php?choix=-89 HTTP/1.1" 200 1870 "-" "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)" "-"
 * #66.249.66.231 - - [22/Mar/2009:07:11:20 +0100] "GET /specialiste.php HTTP/1.1" 404 10743 "-" "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)" "-"
 * #83.198.250.175 - - [22/Mar/2009:07:40:06 +0100] "GET / HTTP/1.1" 500 8714 "http://www.google.fr/search?hl=fr&amp;q=stand+de+foire&amp;meta=&amp;aq=4&amp;oq=stand+de+" "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Wanadoo 6.7; Orange 8.0)" "-"
 * #83.198.250.175 - - [22/Mar/2009:07:40:06 +0100] "GET /style.css HTTP/1.1" 503 1692 "http://www.facades.fr/" "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Wanadoo 6.7; Orange 8.0)" "-"
 *
 * Output:
 * Syntax: /, error: 501, count: 1
 * Syntax: /, error: 500, count: 1
 * Syntax: /style.css, error: 503, count: 1
 */
public class HTTPLogAggregator {

    class LogInfo {
        String errorCode;
        String uri;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LogInfo logInfo = (LogInfo) o;
            return Objects.equals(errorCode, logInfo.errorCode) && Objects.equals(uri, logInfo.uri);
        }

        @Override
        public int hashCode() {
            return Objects.hash(errorCode, uri);
        }

        @Override
        public String toString() {
            return "LogInfo{" +
                    "errorCode='" + errorCode + '\'' +
                    ", uri='" + uri + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<String> logs = Arrays.asList(
                "#88.191.254.20 - - [22/Mar/2009:07:00:32 +0100] \"GET / HTTP/1.0\" 501 8674 \"-\" \"-\" \"-\"",
                "#66.249.66.231 - - [22/Mar/2009:07:06:20 +0100] \"GET /popup.php?choix=-89 HTTP/1.1\" 200 1870 \"-\" \"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)\" \"-\"",
                "#83.198.250.175 - - [22/Mar/2009:07:40:06 +0100] \"GET / HTTP/1.1\" 500 8714 \"http://www.google.fr/search?hl=fr&amp;q=stand+de+foire&amp;meta=&amp;aq=4&amp;oq=stand+de+\" \"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Wanadoo 6.7; Orange 8.0)\" \"-\"",
                "#83.198.250.175 - - [22/Mar/2009:07:40:06 +0100] \"GET / HTTP/1.1\" 500 8714 \"http://www.google.fr/search?hl=fr&amp;q=stand+de+foire&amp;meta=&amp;aq=4&amp;oq=stand+de+\" \"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Wanadoo 6.7; Orange 8.0)\" \"-\"",
                "#83.198.250.175 - - [22/Mar/2009:07:40:06 +0100] \"GET /style.css HTTP/1.1\" 503 1692 \"http://www.facades.fr/\" \"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Wanadoo 6.7; Orange 8.0)\" \"-\""
                                         );
        HTTPLogAggregator httpLogAggregator = new HTTPLogAggregator();
        System.out.println(httpLogAggregator.aggregate(logs));
    }

    public List<String> aggregate(List<String> logs) {
        return logs.stream()
                .map(this::parse)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .map(Map.Entry::toString)
                .toList();
    }

    private LogInfo parse(String log) {
        LogInfo logInfo = new LogInfo();
        String regex = "\\/\\S* HTTP\\/1.\\d{0,1}\" 5\\d{1,2}";
        Pattern pattern = Pattern.compile(regex);
        Pattern errorCodePattern = Pattern.compile("5\\d{1,2}");
        Pattern uriPattern = Pattern.compile("\\/\\S*");
        Matcher matcher = pattern.matcher(log);
        if (!matcher.find()) {
            return null;
        }
        String match = matcher.group();
        Matcher errorCodeMatcher = errorCodePattern.matcher(match);
        Matcher uriMatcher = uriPattern.matcher(match);
        errorCodeMatcher.find();
        uriMatcher.find();
        logInfo.errorCode =  errorCodeMatcher.group();
        logInfo.uri =  uriMatcher.group(0);
        return logInfo;
    }

}
