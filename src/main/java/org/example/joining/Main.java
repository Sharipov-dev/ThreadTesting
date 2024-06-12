package org.example.joining;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        //we want to calculate factorial of all the numbers
        List<Long> inputNumbers = Arrays.asList(0L, 3435L, 1234L, 32324L, 234L);

        List<FactorialThread> threads = new ArrayList<>();

        for(long num : inputNumbers){
            threads.add(new FactorialThread(num));
        }

        for(Thread thread : threads){
            thread.start();
        }

        //this operation guarantees us that all the threads will have calculated the result by the time
        //the following for loop starts execution
        for(Thread thread : threads){
            thread.join();
        }

        for(int i = 0; i< inputNumbers.size(); i++){
            FactorialThread factorialThread = threads.get(i);
            if(factorialThread.isFinished){
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            }
            else{
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }
    }

    public static class FactorialThread extends Thread{
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber){
            this.inputNumber = inputNumber;
        }

        @Override
        public void run(){
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long inputNumber){
            BigInteger tempResult = BigInteger.ONE;

            for(long i  = inputNumber; i > 0;i--){
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }
    }
}
