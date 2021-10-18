package task2;


import javax.swing.*;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// aktive Klasse
public class Download implements Runnable {

    private final JProgressBar balken;
    private Lock lock;
    Condition buttonClicked;
    Condition allFinished;
    JButton startButton;


    // weitere Attribute zur Synchronisation hier definieren

    public Download(JProgressBar balken, Lock lock, Condition buttonClicked, Condition allFinished, JButton startButton) {
        this.balken = balken;
        this.lock = lock;
        this.buttonClicked = buttonClicked;
        this.allFinished = allFinished;
        this.startButton = startButton;


        // ...
    }

    @Override
    public void run() {
        Random random = new Random();

        try {
            lock.lock();

            if (startButton.isEnabled()) {
                buttonClicked.await();
            } else {
                if (balken.getValue() < 100) {
                    updateProgressBar();
                } else {
                    allFinished.notify();
                }
                buttonClicked.signal();


            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
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
