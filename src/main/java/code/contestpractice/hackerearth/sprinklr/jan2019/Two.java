package code.contestpractice.hackerearth.sprinklr.jan2019;

import java.util.HashMap;
import java.util.Map;

public class Two {

    private static Map<Long, Long> beautyCache = new HashMap<>();

    private static Map<Long, Long> sumCache = new HashMap<>();

    public static void main(String[] args) {
        int t = InputUtils.nextInt();
        while(t-- > 0) {
            Long X = InputUtils.nextLong();
            beauty(X/2, 0);
            int sum = 0;
            while (sum < X) {

            }
        }

    }

    static long beauty(long n, long beauty) {
        if (n <= 0) return 0;
        if (null == beautyCache.get(n)) {
            beauty += (n & 1);
            beautyCache.put(n, beauty + beauty(n / 2, beauty));
        }
        return beautyCache.get(n);
    }

}

/*class InputUtils {

    public static BufferedReader getBR() {  return new BufferedReader(new InputStreamReader(in)); }

    public static String[] splitNextLine(BufferedReader br, String regex) {
        try {
            return br.readLine().split(regex);
        } catch (IOException e) {
            return null;
        }
    }
}*/
