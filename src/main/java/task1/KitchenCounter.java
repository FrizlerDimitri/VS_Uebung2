package task1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KitchenCounter {


    private final int maxCounter;
    private int counter;


    private Lock monitor = new ReentrantLock();
    private Condition full = monitor.newCondition();
    private Condition empty = monitor.newCondition();


    public KitchenCounter(int maxCounter) {
        this.maxCounter = maxCounter;
        this.counter = 0;
    }

    public Condition getFull() {
        return full;
    }


    public Condition getEmpty() {
        return empty;
    }

    public Lock getMonitor() {
        return this.monitor;
    }


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getMaxCounter() {
        return maxCounter;
    }

    public void addOne() {

        try {
            monitor.lock();
            if (counter == maxCounter) {
                full.await();
            } else {
                counter++;
                printKitchen();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            monitor.unlock();
        }

    }

    public void subOne() {
        try {
            monitor.lock();
            if (counter == 0) {
                empty.await();
            } else {
                counter--;
                printKitchen();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            monitor.unlock();
        }
    }


    public void printKitchen() {

        String display = "";

        for (int i = 0, j = 0; i < maxCounter; i++, j++) {

            if (j < counter) {
                display += "[x]";
            } else {
                display += "[ ]";
            }
        }
        System.out.println(display);
    }
}
