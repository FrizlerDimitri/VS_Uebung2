package task2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Browser extends JFrame implements ActionListener {

    private int downloads;
    private JProgressBar[] balken;
    private JButton startButton;

    // Deklaration Ihrer Synchronisations-Hilfsklassen hier:


    private final List<Thread> threadList = new ArrayList<Thread>();




    private CyclicBarrier cyclicBarrier;
    private CountDownLatch countDownLatch;


    public Browser(int downloads) {
        super("Mein Download-Browser");
        this.downloads = downloads;


        cyclicBarrier = new CyclicBarrier(downloads);
        countDownLatch = new CountDownLatch(downloads);

        // Initialisierung Ihrer Synchronisations-Hilfsklassen hier:
        // Aufbau der GUI-Elemente:
        balken = new JProgressBar[downloads];
        JPanel zeilen = new JPanel(new GridLayout(downloads, 1));

        startButton = new JButton("Downloads starten");
        startButton.addActionListener(this);



        for (int i = 0; i < downloads; i++) {

            JPanel reihe = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 10));
            balken[i] = new JProgressBar(0, 100);
            balken[i].setPreferredSize(new Dimension(500, 20));
            reihe.add(balken[i]);
            zeilen.add(reihe);


            Download d = new Download(balken[i],startButton,countDownLatch ,cyclicBarrier);
            Thread t = new Thread(d);

            threadList.add(t);
            t.start();

            //balken[i].setValue(50);


            // neue Download-Threads erzeugen und starten
            // ggf. müssen Synchronisations-Objekte im Konstruktor übergeben werden!!
            // balken ist ebenfalls zu übergeben!


        }


        this.add(zeilen, BorderLayout.CENTER);
        this.add(startButton, BorderLayout.SOUTH);


        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void mainTask2(String[] args) throws InterruptedException {
        new Browser(3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Blockierte Threads jetzt laufen lassen:






        startButton.setEnabled(false);
        startButton.setSelected(false);
        startButton.setText("Downloads laufen...");

        // Auf Ende aller Download-Threads warten ... erst dann die Beschriftung ändern
        // Achtung, damit die Oberflaeche "reaktiv" bleibt dies in einem eigenen Runnable ausfuehren!


        Thread waitOfEndThread = new Thread(() -> {

            for (Thread t : threadList) {
                try {
                    t.join();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            startButton.setText("Ende");


        });
        waitOfEndThread.start();


    }

}
