package code.shubham.arrays;

import code.shubham.utils.ArrayUtils;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.valueOf;
import static java.lang.System.in;

/**
 *
 */
public class FillNPosWithMColorsSuchThatItShouldHaveKPairsOfAdjacentDiffColors {

    private static int[][] cache;

    public static void main(String[] args) {
        int n = InputUtils.nextInt();
        int m = InputUtils.nextInt();
        int k = InputUtils.nextInt();
        cache = new int[n][k];
        ArrayUtils.fill2DWithMinusOne(cache);
        System.out.println( m * countWays(1, 0, n, m, k) );
    }

    private static int countWays(int idx, int c, int n, int m, int k) {
        if (n == idx) {
            if (k == c) return 1;
            else return 0;
        }

        if (-1 != cache[idx][c]) return cache[idx][c];

        return cache[idx][c] =
                countWays(idx + 1, c, n, m, k) + ((m-1) * countWays(idx + 1, c + 1, n, m, k));
    }

    private static class InputUtils {

        private static BufferedReader BR;

        public static BufferedReader getBR() {
            if (null == BR) {
                BR = new BufferedReader(new InputStreamReader(in));
            }
            return BR;
        }

        public static String[] splitNextLine() {
            return splitNextLine(BR, " ");
        }

        public static String[] splitNextLine(BufferedReader br) {
            return splitNextLine(br, " ");
        }

        public static String[] splitNextLine(BufferedReader br, String regex) {
            return nextLine().split(regex);
        }

        public static String nextLine() {
            return nextLine(getBR());
        }

        public static String nextLine(BufferedReader br) {
            try {
                return br.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public static int toInteger(String s) {
            return valueOf(s);
        }

        public static long toLong(String s) {
            return Long.valueOf(s);
        }

        public static int nextInt() {
            return toInteger(nextLine());
        }

        public static long nextLong() {
            return toLong(nextLine());
        }

        public static class FastReaderMain
        {
            static class FastReader
            {
                BufferedReader br;
                StringTokenizer st;

                public FastReader()
                {
                    br = new BufferedReader(new
                            InputStreamReader(System.in));
                }

                String next()
                {
                    while (st == null || !st.hasMoreElements())
                    {
                        try
                        {
                            st = new StringTokenizer(br.readLine());
                        }
                        catch (IOException  e)
                        {
                            e.printStackTrace();
                        }
                    }
                    return st.nextToken();
                }

                int nextInt()
                {
                    return Integer.parseInt(next());
                }

                long nextLong()
                {
                    return Long.parseLong(next());
                }

                double nextDouble()
                {
                    return Double.parseDouble(next());
                }

                String nextLine()
                {
                    String str = "";
                    try
                    {
                        str = br.readLine();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    return str;
                }
            }

            public static class InputReader {
                private InputStream stream;
                private byte[] buf = new byte[1024];

                private int curChar;

                private int numChars;

                public InputReader(InputStream stream) {
                    this.stream = stream;
                }

                public int read() {
                    if (numChars == -1)
                        throw new RuntimeException();
                    if (curChar >= numChars) {
                        curChar = 0;
                        try {
                            numChars = stream.read(buf);
                        } catch (IOException e) {
                            throw new RuntimeException();
                        }
                        if (numChars <= 0)
                            return -1;
                    }
                    return buf[curChar++];
                }

                public String readString() {
                    final StringBuilder stringBuilder = new StringBuilder();
                    int c = read();
                    while (isSpaceChar(c))
                        c = read();
                    do {
                        stringBuilder.append(c);
                        c = read();
                    } while (!isSpaceChar(c));
                    return stringBuilder.toString();
                }

                public int readInt() {
                    int c = read();
                    while (isSpaceChar(c))
                        c = read();
                    int sgn = 1;
                    if (c == '-') {
                        sgn = -1;
                        c = read();
                    }
                    int res = 0;
                    do {
                        res *= 10;
                        res += c - '0';
                        c = read();
                    } while (!isSpaceChar(c));
                    return res * sgn;
                }

                public long readLong() {
                    int c = read();
                    while (isSpaceChar(c))
                        c = read();
                    int sgn = 1;
                    if (c == '-') {
                        sgn = -1;
                        c = read();
                    }
                    long res = 0;
                    do {
                        res *= 10;
                        res += c - '0';
                        c = read();
                    } while (!isSpaceChar(c));
                    return res * sgn;
                }

                public boolean isSpaceChar(int c) {
                    return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
                }
            }
        }

        class Reader
        {
            final private int BUFFER_SIZE = 1 << 16;
            private DataInputStream din;
            private byte[] buffer;
            private int bufferPointer, bytesRead;

            public Reader()
            {
                din = new DataInputStream(System.in);
                buffer = new byte[BUFFER_SIZE];
                bufferPointer = bytesRead = 0;
            }

            public Reader(String file_name) throws IOException
            {
                din = new DataInputStream(new FileInputStream(file_name));
                buffer = new byte[BUFFER_SIZE];
                bufferPointer = bytesRead = 0;
            }

            public String readLine() throws IOException
            {
                byte[] buf = new byte[64]; // line length
                int cnt = 0, c;
                while ((c = read()) != -1)
                {
                    if (c == '\n')
                        break;
                    buf[cnt++] = (byte) c;
                }
                return new String(buf, 0, cnt);
            }

            public int nextInt() throws IOException
            {
                int ret = 0;
                byte c = read();
                while (c <= ' ')
                    c = read();
                boolean neg = (c == '-');
                if (neg)
                    c = read();
                do
                {
                    ret = ret * 10 + c - '0';
                }  while ((c = read()) >= '0' && c <= '9');

                if (neg)
                    return -ret;
                return ret;
            }

            public long nextLong() throws IOException
            {
                long ret = 0;
                byte c = read();
                while (c <= ' ')
                    c = read();
                boolean neg = (c == '-');
                if (neg)
                    c = read();
                do {
                    ret = ret * 10 + c - '0';
                }
                while ((c = read()) >= '0' && c <= '9');
                if (neg)
                    return -ret;
                return ret;
            }

            public double nextDouble() throws IOException
            {
                double ret = 0, div = 1;
                byte c = read();
                while (c <= ' ')
                    c = read();
                boolean neg = (c == '-');
                if (neg)
                    c = read();

                do {
                    ret = ret * 10 + c - '0';
                }
                while ((c = read()) >= '0' && c <= '9');

                if (c == '.')
                {
                    while ((c = read()) >= '0' && c <= '9')
                    {
                        ret += (c - '0') / (div *= 10);
                    }
                }

                if (neg)
                    return -ret;
                return ret;
            }

            private void fillBuffer() throws IOException
            {
                bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
                if (bytesRead == -1)
                    buffer[0] = -1;
            }

            private byte read() throws IOException
            {
                if (bufferPointer == bytesRead)
                    fillBuffer();
                return buffer[bufferPointer++];
            }

            public void close() throws IOException
            {
                if (din == null)
                    return;
                din.close();
            }
        }

        public static class ReaderMain
        {
            public static void main(String[] args) throws IOException
            {
                input.Reader s=new input.Reader();
                int n = s.nextInt();
                int k = s.nextInt();
                int count=0;
                while (n-- > 0)
                {
                    int x = s.nextInt();
                    if (x%k == 0)
                        count++;
                }
                System.out.println(count);
            }
        }
    }

}
