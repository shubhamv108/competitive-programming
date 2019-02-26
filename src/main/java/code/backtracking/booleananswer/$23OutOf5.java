package code.backtracking.booleananswer;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

class Reader {
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader() {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public Reader(String file_name) throws IOException {
        din = new DataInputStream(new FileInputStream(file_name));
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException {
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

    public int nextInt() throws IOException {
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

    public long nextLong() throws IOException {
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

    public double nextDouble() throws IOException {
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

    private void fillBuffer() throws IOException {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1)
            buffer[0] = -1;
    }

    private byte read() throws IOException {
        if (bufferPointer == bytesRead)
            fillBuffer();
        return buffer[bufferPointer++];
    }

    public void close() throws IOException {
        if (din == null)
            return;
        din.close();
    }
}

class $23OutOf5Solver {
    int[] a;
    boolean[] visited;

    $23OutOf5Solver(int[] a) {
        this.a = a;
        this.visited = new boolean[a.length];
    }

    boolean allVisited() {
        for (int i = 0; i < 5; i++) if (!visited[i]) return false;
        return true;
    }

    boolean solveHelper(int ans) {

        if (allVisited()) {
            if (ans == 23) return true;
            return false;
        }

        for (int i = 0; i < 5; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (solveHelper(ans + a[i])
                        || solveHelper(ans - a[i])
                        || solveHelper(ans * a[i]))
                    return true;
                visited[i] = false;
            }
        }
        return false;
    }

    boolean solve() {
        for (int i = 0; i < 5; i++) {
            int ans = a[i];
            visited[i] = true;
            if (solveHelper(ans)) return true;
            visited[i] = false;
        }
        return false;
    }
}

public class $23OutOf5 {
//    public static void main(String[] args) {
//        Reader reader = new Reader();
//        try {
//            int[] a = new int[5];
//            int t = reader.nextInt();
//            while (t-- > 0) {
//                for (int i=0;i<5;i++)
//                    a[i] = reader.nextInt();
//                if(new $23OutOf5Solver(a).solve()) System.out.printf("%s", "YES");
//                else System.out.printf("%s", "NO");
//            }
//        } catch(IOException e) {
//            throw new IllegalArgumentException("Invalid Input", e);
//        }
//    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int[] a = new int[5];
            while(sc.hasNext()) {
                boolean shouldBeProcessed = false;
                for (int i=0;i<5;i++) {
                    a[i] = Integer.parseInt(sc.next());
                    if(!shouldBeProcessed && a[i] != 0) shouldBeProcessed = true;
                }
                if(shouldBeProcessed) {
                    if (new $23OutOf5Solver(a).solve()) System.out.printf("%s\n", "YES");
                    else System.out.printf("%s\n", "NO");
                }
            }
        } catch(Exception e) {
            throw new IllegalArgumentException("Invalid Input", e);
        }
    }
}
