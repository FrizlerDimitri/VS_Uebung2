package task1;

import java.util.concurrent.locks.Lock;

import static java.lang.Thread.sleep;

public class Waiter implements Runnable {

    private KitchenCounter counter;
    private String name;


    public Waiter(KitchenCounter counter, String name) {
        this.counter = counter;
        this.name = name;
    }

    public KitchenCounter getCounter() {
        return counter;
    }

    public void setCounter(KitchenCounter counter) {
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        while (true) {

            try {
                sleep(1000);
                put();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private void put() throws InterruptedException {

        counter.addOne();
    }
}
