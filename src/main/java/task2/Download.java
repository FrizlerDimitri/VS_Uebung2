package task2;


import javax.swing.*;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

// aktive Klasse
public class Download implements Runnable {

    private final JProgressBar balken;

    JButton startButton;

    private CountDownLatch buttonPressed;
    private CountDownLatch downloadFinished;

    // weitere Attribute zur Synchronisation hier definieren

    public Download(JProgressBar balken, JButton startButton, CountDownLatch buttonPressed, CountDownLatch downloadFinished) {
        this.balken = balken;
        this.startButton = startButton;
        this.buttonPressed = buttonPressed;
        this.downloadFinished = downloadFinished;
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
            buttonPressed.await();

            Random random = new Random();

            while (this.balken.getValue() < 100) {
                updateProgressBar();
                try {
                    Thread.sleep(1000 * random.nextInt(2));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            downloadFinished.countDown();

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
