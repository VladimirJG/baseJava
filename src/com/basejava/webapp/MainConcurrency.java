package com.basejava.webapp;

public class MainConcurrency {
    private static int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
       /* new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        }.start();*/

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();*/
        new Thread(() -> System.out.println(Thread.currentThread().getName() + Thread.currentThread().getState())).start();
        System.out.println(thread0.getState());


        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    inc();
                }
            }).start();
        }
        Thread.sleep(500);
        System.out.println(counter);

        final String lock1 = "lock1";
        final String lock2 = "lock2";
        deadLock(lock1, lock2);
        deadLock(lock2, lock1);
    }

    private static void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println("Waiting" + lock1);
            synchronized (lock1) {
                System.out.println("Holding" + lock1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Waiting" + lock2);
                synchronized (lock2) {
                    System.out.println("Holding" + lock2);

                }
            }
        }).start();
    }

    private static void inc() {
        double a = Math.sin(13.);
        synchronized (LOCK) {
            counter++;
        }
    }
}
