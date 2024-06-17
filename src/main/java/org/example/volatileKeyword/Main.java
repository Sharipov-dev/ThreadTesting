package org.example.volatileKeyword;

public class Main {
    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();

        Thread thread = new Thread(() -> {
            Object object = exchanger.getObject();
            System.out.println("Received object: " + object);
        });
        thread.start();

        Thread thread1 = new Thread(() -> {
            System.out.println("Setting an object...");
            exchanger.setObject(new Object());
        });
        thread1.start();
    }


    public static class Exchanger {
        private volatile Object object = null;
        private volatile boolean hasObject = false;

        public synchronized void setObject(Object object) {
            this.object = object;
            this.hasObject = true;
            notifyAll();
        }

        public synchronized Object getObject() {
            while (!this.hasObject) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            this.hasObject = false;
            return object;
        }
    }
}