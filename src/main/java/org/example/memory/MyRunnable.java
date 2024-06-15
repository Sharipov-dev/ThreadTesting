package org.example.memory;

public class MyRunnable implements Runnable{

    public Object myObject = null;

    public MyRunnable(){
    }
    public MyRunnable(Object myObject){
        this.myObject = myObject;
    }

    public void doSomething(){

        Object newObject = new Object(); // this object won't be shared among other threads

    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        System.out.println("Thread is running. Object address: " + myObject);
    }
}
