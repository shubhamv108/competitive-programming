package code.backtracking.pathprinting;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

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

class Solver1 {

    int count = 0;

    boolean generateSolutionHelper(int[][] m, int R, int C, int r, int c, int[][] s /*, boolean[][] notAlreadyVisited*/) {
        if(r > R || c > C || c < 0 || r < 0) {
            if ((r == R + 1 && c == C)  || (r == R && c == C+1)) {
                count++;
                return true;
            }
            return false;
        }
        if(s[r][c] == 1)
            return false;
        if(m[r][c] == 1) {
            s[r][c] = 1;
            //for first path
            /*if(generateSolutionHelper(m, R, C, r - 1, c, s)
                    || generateSolutionHelper(m, R, C, r + 1, c, s)
                    || generateSolutionHelper(m, R, C, r, c - 1, s)
                    || generateSolutionHelper(m, R, C, r, c + 1, s)) {
                return true;
            } else {
                s[r][c] = 0;
                return false;
            }*/
            //----------------------------------------------------------
            //for all paths
            if(!generateSolutionHelper(m, R, C, r - 1, c, s) &&
            !generateSolutionHelper(m, R, C, r, c - 1, s) &&
            !generateSolutionHelper(m, R, C, r, c + 1, s) &&
            !generateSolutionHelper(m, R, C, r + 1, c, s)) {
                s[r][c] = 0;
                return false;
            } else {
                return true;
            }
            //----------------------------------------------------------
        } else
            return false;
    }

    void generateSolution(int[][] m, int[][] s) {
        generateSolutionHelper(m, m.length-1, m[0].length - 1, 0, 0, s /*, new boolean[4][4]*/);
        for(int i=0; i<s.length; i++) {
            for(int j=0;j<s[0].length;j++) System.out.printf("%d ", s[i][j]);
            System.out.printf("\n");
        }
        System.out.printf("\n");
        System.out.println("count:: " + count);
    }

    void solve(int[][] grid, int[][] sol) {
        generateSolution(grid, sol);
    }
}

public class RatInTheMaze {

    /*public static void main(String[] args) throws IOException {
//        int[][] grid = {
//                {1, 0, 0, 0},
//                {1, 1, 1, 1},
//                {0, 0, 1, 0},
//                {1, 1, 1, 1}
//        };
//        new Solver1().solve(grid, new int[grid.length][grid[0].length]);
        Reader reader = new Reader();
        int n = reader.nextInt();
        int[][] a = new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                a[i][j] = reader.nextInt();
            }
        }
        new Solver1().solve(a, new int[n][n]);
    }*/

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
        new Solver1().solve(a, new int[n][n]);
    }
}