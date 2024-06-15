package org.example.synchronizedKeyword;

public class Main {

    public static void main(String[] args) {

        SynchronizedExchanger synchronizedExchanger = new SynchronizedExchanger();

        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i < 1000; i++) {
                        synchronizedExchanger.setObj(i);
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    for(int i = 0; i < 1000; i++){
                        System.out.println(synchronizedExchanger.getObj());
                    }
                }

        );

        thread1.start();
        thread2.start();
    }


}
