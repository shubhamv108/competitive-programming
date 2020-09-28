package code.randomcode.multihreading;

public class PrintOddEvenWithTwoThreads {

    void print() {
        Thread t1 = new Thread(new PrintOddEvenRunnable());
        Thread t2 = new Thread(new PrintOddEvenRunnable());

    }

}

class PrintOddEvenRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if ((i & 1) == 1) printOdd();
            else printEven();
        }
    }

    synchronized void printOdd() {
        
    }

    synchronized void printEven() {

    }

}

