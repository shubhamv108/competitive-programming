package code.contestpractice.codechef;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

class Solver {

    int[][] a;
    int n;
    int[][] s;
    int count;

    Solver(int[][] a, int n) {
        this.a = a;
        this.n = n;
        this.s = new int[n][n];
        this.count = 0;
    }

    void calculateCountOfPathsHelper(int r, int c) {
        if(r == n  || c == n || c < 0 || r < 0 || a[r][c] == 1)
            return;
        if (r == n-1 && c == n-1) {
            if(a[r][c] == 0) count++;
            return;
        }
        a[r][c] = 1;
        calculateCountOfPathsHelper(r - 1, c);
        calculateCountOfPathsHelper(r, c - 1);
        calculateCountOfPathsHelper(r, c + 1);
        calculateCountOfPathsHelper(r + 1, c);
        a[r][c] = 0;
    }

    void calculateCountOfPaths() {
        calculateCountOfPathsHelper(0, 0);
    }

    int getCountOfPaths() {
        calculateCountOfPaths();
        return count;
    }

    int solve() {
        calculateCountOfPaths();
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) System.out.print(s[i][j] + " ");
            System.out.println();
        }
        return getCountOfPaths();
    }
}

class Reader {
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

public class RatInTheMaze {
    public static void main(String[] args) {
        Reader reader = new Reader();
        int n;
        int[][] a;
        try {
            n = reader.nextInt();
            a = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    a[i][j] = reader.nextInt();
            }
        } catch(IOException e) {
            throw new IllegalArgumentException("Illegal input argument exception", e);
        }
        System.out.printf("count:: %d", new Solver(a, n).solve());
    }
}
