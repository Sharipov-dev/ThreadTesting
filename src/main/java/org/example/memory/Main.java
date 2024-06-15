package org.example.memory;

public class Main {
    public static void main(String[] args) {
        Object object = new Object();

        /////// Here two runnable classes will refer to the same object
//        MyRunnable myRunnable1 = new MyRunnable(object);
//        MyRunnable myRunnable2 = new MyRunnable(object);
//
//        Thread thread1 = new Thread(myRunnable1);
//        Thread thread2 = new Thread(myRunnable2);

        MyRunnable myRunnable1 = new MyRunnable(object);

        Thread thread1 = new Thread(myRunnable1);
        Thread thread2 = new Thread(myRunnable1);

        thread1.start();
        thread2.start();

    }


}
