package code.shubham.java;

public class ThreadJoin {

    class Solution extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 20; i++) {
                try {
                    Thread.sleep(100l);
                } catch (Exception ex) {
                }
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadJoin threadJoin = new ThreadJoin();
        Thread h1 = threadJoin.new Solution();
        Thread h2 = threadJoin.new Solution();
        h1.start();
        h1.join();

        h2.start();
        if (h2.isAlive())
            System.out.println("h2 is alive");
    }
}
