package com.company;

import java.awt.GridBagConstraints;
import java.io.PrintStream;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JLabel;

public class thread implements Runnable {
    Thread thrd;
    int grid;
    String text;
    public boolean pause = true;
    public boolean exit = false;
    static String[] veshi = new String[]{"Smatphone", "Video camera", "Processor", "Graphic Card", "Keyboard", "Notebook", "HDD", "SSD", "Tablet", "Playstation", "Xbox"};
    int[] temp = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    JLabel jl;

    thread(String name, int a, int i) {
        this.thrd = new Thread(this, name);
        this.thrd.setPriority(a);
        this.thrd.start();
        this.grid = i;
    }

    public static synchronized void y_inc() {
        ++swing.y_grid;
    }

    public void inc(int a) {
        int var10002 = this.temp[a]++;
    }

    public void run() {
        y_inc();
        this.jl = new JLabel("Openning shop...");
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 0.0D;
        c.weightx = 0.0D;
        c.gridx = 0;
        c.gridy = this.grid;
        c.gridheight = 1;
        c.gridwidth = 4;
        swing.j.add(this.jl, c);

        while(this.pause && !this.exit) {
            try {
                int b = ThreadLocalRandom.current().nextInt(2, 7);
                b *= 1000;
                Thread.sleep((long)b);
                PrintStream var10000 = System.out;
                String var10001 = this.thrd.getName();
                var10000.println(var10001 + " is " + this.thrd.isInterrupted());
                int a = ThreadLocalRandom.current().nextInt(0, 10);
                var10001 = veshi[a];
                this.text = var10001 + " was sold in " + this.thrd.getName();
                this.inc(a);
                this.jl.setText(this.text);
                int var10002 = swing.bd[a]++;
                var10001 = veshi[a];
                System.out.println(var10001 + " was sold in " + this.thrd.getName());
            } catch (InterruptedException var5) {
                Thread.currentThread().interrupt();
                var5.getCause();
            }

            while(!this.pause && !this.exit) {
                try {
                    this.jl.setText("shop is taking a brake");
                    Thread.sleep(20L);
                    System.out.println("sleeping/");
                } catch (InterruptedException var4) {
                    var4.getMessage();
                }
            }
        }

        swing.j.remove(this.jl);
        System.out.println("ended");
    }
}
