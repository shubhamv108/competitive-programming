package code.arrays;

public class SymetricDoubleTriangle {

    // print alternate x o beginning with x
    static void printx(int n)
    {
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0)
                System.out.print("x ");
            else
                System.out.print("o ");
        }
        return;
    }

    // print alternate x o beginning with o
    static void printo(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0)
                System.out.print("o ");
            else
                System.out.print("x ");
        }
        return;
    }

    // print the pattern for n
    static void printPattern(int n) {
        // upper half n-1 lines for
        // odd, n-2 lines for even
        int x = n;

        if (n % 2 == 0)
            x = x - 1;

        // number of spaces to leave in each line
        int p = n - 1;

        // number of characters in each line
        int s = 1;

        // prints double lines in each iteration
        for (int i = 1; i <= (x - 1) / 2; i++) {
            for (int j = 1; j <= p; j++) {
                System.out.print(" ");
            }

            if (i % 2 != 0)
                printx(s);
            else
                printo(s);

            System.out.println();
            p++;

            for (int j = 1; j <= p; j++)
                System.out.print(" ");

            if (i % 2 != 0)
                printx(s);
            else
                printo(s);

            System.out.println();

            p--;
            s++;
        }

        // extra upper middle for even
        if (n % 2 == 0) {
            for (int i = 1; i <= p; i++)
                System.out.print(" ");

            if (n % 4 != 0)
                printx(n / 2);
            else
                printo(n / 2);

            System.out.println();
        }

        // middle line
        if (n % 2 != 0)
            printx(n);

        else {
            if (n % 4 != 0) {
                printx(n / 2);
                printx(n / 2);
            }
            else {
                printx(n / 2);
                printo(n / 2);
            }
        }

        System.out.println();

        // extra lower middle for even
        if (n % 2 == 0) {
            System.out.print(" ");
            printx(n / 2);
            System.out.println();
        }

        // lower half
        p = 1;

        if (n % 2 == 0) {
            x--;
            p = 2;
        }

        int q = x / 2;

        // one line for each iteration
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= p; j++)
                System.out.print(" ");

            printx(q);

            if (i % 2 == 0)
                q--;

            System.out.println();

            p++;
        }

        System.out.println();
    }

    // Driver code
    public static void main (String[] args) {
        int n = 7;
        printPattern(n);

        n = 8;
        printPattern(n);
    }
}
