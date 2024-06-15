package org.example.synchronizedKeyword;

public class Main2 {
    public static void main(String[] args) {
        Object monitor1 = new Object();

        SharedMonitorObject instance1 = new SharedMonitorObject(monitor1);
        SharedMonitorObject instance2 = new SharedMonitorObject(monitor1);

        //now both instances block each other
        instance1.increaseTheCounter();
        instance2.increaseTheCounter();

        Object monitor2 = new Object();

        //however this instance can be blocked by instance1 and instance2 as it has another monitor object
        SharedMonitorObject instance3 = new SharedMonitorObject(monitor2);
    }
}
