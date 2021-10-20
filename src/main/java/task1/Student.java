package task1;

import task1.KitchenCounter;

import java.util.concurrent.locks.Lock;

import static java.lang.Thread.sleep;

public class Student implements Runnable {

    private KitchenCounter counter;
    private String name;

    public Student(KitchenCounter counter, String name) {
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
                sleep(2000);
                take();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

    private void take() {

        counter.subOne();

    }

}
