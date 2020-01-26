package code.dates;

import java.util.Date;

public class DatesUtil {

    private static long MILLIS_IN_A_DAY = 86400000l;

    public static void main (String[] args) throws java.lang.Exception {

        System.out.print(
                new Date(System.currentTimeMillis() + (90l * 86400000l))
        );
    }

    private static Date addDays(Date date, int days) {
        return new Date(date.getTime() + (days * MILLIS_IN_A_DAY));
    }

}
