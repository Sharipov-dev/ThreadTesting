package org.example.synchronizedKeyword;

public class SynchronizedExchangerStatic {
    protected static Object object = null;

    public static synchronized Object getObject() {
        return object;
    }

    public static Object getObj(){
        synchronized (SynchronizedExchangerStatic.class){
            return object;
        }
    }

    public static synchronized void setObject(Object o) {
        object = o;
    }

    public void setObj(Object o){
        synchronized (SynchronizedExchangerStatic.class){
            object = o;
        }
    }
}
