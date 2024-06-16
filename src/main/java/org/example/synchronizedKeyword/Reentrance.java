package org.example.synchronizedKeyword;

public class Reentrance {

    public int count = 0;

    public synchronized void inc(){
        this.count++;
    }

    public synchronized int incAndGet(){
        inc();
        return count;
    }

}
