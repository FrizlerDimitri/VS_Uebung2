package task2;


import javax.swing.*;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// aktive Klasse
public class Download implements Runnable {

    private final JProgressBar balken;

    JButton startButton;

    private CountDownLatch countDownLatch;
    private CyclicBarrier cyclicBarrier;


    // weitere Attribute zur Synchronisation hier definieren

    public Download(JProgressBar balken, JButton startButton, CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier) {
        this.balken = balken;
        this.startButton = startButton;
        this.countDownLatch = countDownLatch;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        /*
        Random random=new Random();
        lock.lock();
        try {
            buttonClicked.await();
            while (balken.getValue()<balken.getMaximum())
            {
                updateProgressBar();
                buttonClicked.await(random.nextInt(2), TimeUnit.SECONDS);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        */


        try {
            countDownLatch.await();

            Random random = new Random();

            while (this.balken.getValue() < 100) {
                updateProgressBar();
                try {
                    Thread.sleep(1000 * random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    private void updateProgressBar() {

        balken.setValue(balken.getValue() + 1);

    }



    /*  hier die Methode definieren, die jeweils den Balken weiterschiebt
     *  Mit balken.getMaximum() bekommt man den Wert fuer 100 % gefuellt
     *  Mit balken.setValue(...) kann man den Balken einstellen (wieviel gefuellt) dargestellt wird
     *  Setzen Sie den value jeweils und legen Sie die Methode dann für eine zufaellige Zeit schlafen
     */


}
