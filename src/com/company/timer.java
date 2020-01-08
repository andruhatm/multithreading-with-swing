package com.company;

class timer implements Runnable {
    Thread thread;
    private static int millisecounds;
    private static int secounds;
    private static int minutes;
    private static int hours;
    public static boolean state = true;
    public static boolean exit = false;

    timer(String name, int h, int min, int sec, int millies) {
        this.thread = new Thread(this, name);
        millisecounds = millies;
        secounds = sec;
        minutes = min;
        hours = h;
        this.thread.start();
    }

    public void run() {
        label41:
        while(true) {
            if (state && !exit) {
                try {
                    Thread.sleep(1L);
                    if (millisecounds > 1000) {
                        millisecounds = 0;
                        ++secounds;
                    }

                    if (secounds > 59) {
                        secounds = 0;
                        ++minutes;
                    }

                    if (minutes > 59) {
                        minutes = 0;
                        ++hours;
                    }

                    ++millisecounds;
                    swing.jl1.setText(hours + ": " + minutes + ": " + secounds);
                } catch (Exception var3) {
                    var3.getMessage();
                }

                while(true) {
                    if (state || exit) {
                        continue label41;
                    }

                    try {
                        Thread.sleep(1L);
                    } catch (InterruptedException var2) {
                        var2.printStackTrace();
                    }
                }
            }

            millisecounds = 0;
            secounds = 0;
            minutes = 0;
            hours = 0;
            swing.t_stp_r();
            swing.t_exit_r();
            swing.jl1.setText("00:00:00");
            System.out.println("ends");
            return;
        }
    }
}