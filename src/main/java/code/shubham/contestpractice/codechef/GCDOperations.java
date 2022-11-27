package code.shubham.contestpractice.codechef;

import static java.lang.Integer.valueOf;
import static java.lang.System.in;

public class GCDOperations {

    class Solution {
        Solution() {}

        void solve() {}

        int gcd(int a, int b) {
            if (b == 0) return a;
            return gcd(b, a%b);
        }
    }

    static class InputUtils {

        private static java.io.BufferedReader BR;

        public static java.io.BufferedReader getBR() {
            if (null == BR) {
                BR = new java.io.BufferedReader(new java.io.InputStreamReader(in));
            }
            return BR;
        }

        public static Integer[] nextIntegerLine() {
            return java.util.Arrays.stream(splitNextLine()).map(Integer::valueOf).toArray(Integer[]::new);
        }

        public static int[] nextIntLine() {
            return java.util.Arrays.stream(splitNextLine()).mapToInt(Integer::valueOf).toArray();
        }

        public static long[] nextLongLine() {
            return java.util.Arrays.stream(splitNextLine()).mapToLong(Long::valueOf).toArray();
        }

        public static String[] splitNextLine() {
            return splitNextLine(BR, " ");
        }

        public static String[] splitNextLine(java.io.BufferedReader br) {
            return splitNextLine(br, " ");
        }

        public static String[] splitNextLine(java.io.BufferedReader br, String regex) {
            return nextLine().split(regex);
        }

        public static String nextLine() {
            return nextLine(getBR());
        }

        public static String nextLine(java.io.BufferedReader br) {
            try {
                return br.readLine();
            } catch (java.io.IOException e) {
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
                java.io.BufferedReader br;
                java.util.StringTokenizer st;

                public FastReader()
                {
                    br = new java.io.BufferedReader(new
                            java.io.InputStreamReader(System.in));
                }

                String next()
                {
                    while (st == null || !st.hasMoreElements())
                    {
                        try
                        {
                            st = new java.util.StringTokenizer(br.readLine());
                        }
                        catch (java.io.IOException e)
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
                    catch (java.io.IOException e)
                    {
                        e.printStackTrace();
                    }
                    return str;
                }
            }

            public static class InputReader {
                private java.io.InputStream stream;
                private byte[] buf = new byte[1024];

                private int curChar;

                private int numChars;

                public InputReader(java.io.InputStream stream) {
                    this.stream = stream;
                }

                public int read() {
                    if (numChars == -1)
                        throw new RuntimeException();
                    if (curChar >= numChars) {
                        curChar = 0;
                        try {
                            numChars = stream.read(buf);
                        } catch (java.io.IOException e) {
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
            private java.io.DataInputStream din;
            private byte[] buffer;
            private int bufferPointer, bytesRead;

            public Reader()
            {
                din = new java.io.DataInputStream(System.in);
                buffer = new byte[BUFFER_SIZE];
                bufferPointer = bytesRead = 0;
            }

            public Reader(String file_name) throws java.io.IOException
            {
                din = new java.io.DataInputStream(new java.io.FileInputStream(file_name));
                buffer = new byte[BUFFER_SIZE];
                bufferPointer = bytesRead = 0;
            }

            public String readLine() throws java.io.IOException
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

            public int nextInt() throws java.io.IOException
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

            public long nextLong() throws java.io.IOException
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

            public double nextDouble() throws java.io.IOException
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

            private void fillBuffer() throws java.io.IOException
            {
                bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
                if (bytesRead == -1)
                    buffer[0] = -1;
            }

            private byte read() throws java.io.IOException
            {
                if (bufferPointer == bytesRead)
                    fillBuffer();
                return buffer[bufferPointer++];
            }

            public void close() throws java.io.IOException
            {
                if (din == null)
                    return;
                din.close();
            }
        }

        public static class ReaderMain
        {
            public static void main(String[] args) throws java.io.IOException
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

    public static void main(String[] args) {
        int T = InputUtils.nextInt();

    }

}
