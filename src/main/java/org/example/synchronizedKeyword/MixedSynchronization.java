package org.example.synchronizedKeyword;

public class MixedSynchronization {

    public static Object object = null;

    public Object objectInstance = null;
    public static synchronized void setObject (Object o){
        object = o;
    }

    public synchronized void setObjectInstance (Object o){
        objectInstance = o;
    }

}
