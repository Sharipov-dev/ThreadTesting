package org.example.synchronizedKeyword;

public class SynchronizedExchanger {

    protected Object object = null;

    public synchronized Object getObject() {
        return object;
    }

    public Object getObj(){
        synchronized (this){
            return this.object;
        }
    }

    public synchronized void setObject(Object object) {
        this.object = object;
    }

    public void setObj(Object object){
        synchronized (this){
            this.object = object;
        }
    }
}
