package org.example.synchronizedKeyword;

public class MultipleMonitorObjects {

    protected Object object1;
    protected Object object2;

    private int counter1 = 0;
    private int counter2 = 0;

    public void increaseCounter1(){
        synchronized (this.object1){
            counter1++;
        }
    }

    public void increaseCounter2(){
        synchronized (this.object2){
            counter2++;
        }
    }
}
