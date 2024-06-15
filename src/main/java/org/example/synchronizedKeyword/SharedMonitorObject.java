package org.example.synchronizedKeyword;

public class SharedMonitorObject {

    protected Object monitorObject = null;

    public int counter = 0;
    public SharedMonitorObject(Object o) {
        if(o == null){
            throw new IllegalArgumentException("Object can not be null"); // this movement is needed cuz if o will be null it will cause NullPointerException in the future
        }
        this.monitorObject = o;
    }

    public void increaseTheCounter(){

        synchronized (monitorObject){
            counter++;
        }
    }


}
