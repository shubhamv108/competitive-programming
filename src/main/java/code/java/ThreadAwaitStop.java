package code.java;

public class ThreadAwaitStop {
    class Solution extends Thread {
        private volatile boolean spin = true;

        public void run() {
            int i = 0;
            while (spin)
                System.out.println("Spinning"+i++);
        }

        public void stopSpin() {
            this.spin = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadAwaitStop threadAwaitStop = new ThreadAwaitStop();
        Solution t1 = threadAwaitStop.new Solution();
        t1.start();
        Thread.sleep(1000l);
        t1.stopSpin();
    }
}
