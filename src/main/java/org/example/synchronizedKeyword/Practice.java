package org.example.synchronizedKeyword;

public class Practice {

    public static void main(String[] args) {

        Integer integer = 0;
        MyRunnable myRunnable1 = new MyRunnable(integer);

        Thread thread1 = new Thread(myRunnable1);
        Thread thread2 = new Thread(myRunnable1);
        thread1.start();
        thread2.start();
        }
    public static class MyRunnable implements Runnable{
        public Integer count;

        public MyRunnable(Integer count){
            this.count = count;
        }

        public synchronized void inc(){
            count++;
        }

        public synchronized int getCount(){
            return count;
        }

        @Override
        public void run(){
            this.inc();
            System.out.println("Mutual count is equal to: " + this.getCount() + " done by: " + Thread.currentThread().getName());
        }
    }

}
