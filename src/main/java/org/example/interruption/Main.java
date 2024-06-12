package org.example.interruption;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputation(BigInteger.valueOf(10000000L),BigInteger.valueOf(20000000L)));

        //if we want our application to be stopped as soon as main Thread runs out the instructions we should specify our thread
        //as a Daemon Thread
//        thread.setDaemon(true);

        thread.start();

        //if we don't stop the computation, calculation will be being performed for a long time
        thread.interrupt();



    }

    public static class LongComputation implements Runnable{

        private BigInteger base;
        private BigInteger power;

        public LongComputation(BigInteger base, BigInteger power){
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + "=" + "=" + pow(base,power));
        }

        public BigInteger pow(BigInteger base, BigInteger power){
            BigInteger result = BigInteger.ONE;
            for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);

            }
            return result;
        }
    }

}
