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
        counter++;
    }

    public void subOne() {
        counter--;
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
