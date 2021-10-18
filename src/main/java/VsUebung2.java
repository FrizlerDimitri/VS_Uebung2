import task1.KitchenCounter;
import task1.Student;
import task1.Waiter;
import task2.Browser;

public class VsUebung2 {

    public static void main(String[] args) throws InterruptedException {


        //------------------------Task 2 a-----------------------------------------------
        System.out.println("Welcome ....");

        KitchenCounter theke = new KitchenCounter(4);
        Thread k1 = new Thread(new Waiter(theke, "Kellner-1"));
        k1.setDaemon(true);
        k1.start();
        Thread k2 = new Thread(new Waiter(theke, "Kellner-2"));
        k2.setDaemon(true);
        k2.start();
        for (int i = 1; i <= 8; i++) {
            Thread k = new Thread(new Student(theke, "task1.Student-" + i));
            k.setDaemon(true);
            k.start();

        }


        Thread.sleep(10000);
        System.out.println("We are closing ....");





        //Browser.mainTask2(args);

    }


}
