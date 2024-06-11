package org.example.example1;

public class Main {
    public static final int MAX_PASSWORD = 9999;
    public static void main(String[] args) {

        Vault vault = new Vault(1234);


        HackerThread ascendingHacker = new AscendingHackerThread(vault);
        ascendingHacker.start();


        HackerThread descendingHacker = new DescendingHackerThread(vault);
        descendingHacker.start();


        Thread police = new PoliceClass();
        police.start();
    }

    public static class Vault{
        private int password;
        public Vault(int password){
            this.password = password;
        }

        public boolean isCorrectPassword (int guess) {
            try{
                Thread.sleep(5);
            }
            catch(InterruptedException e){

            }
            return this.password == guess;
        }
    }
    private static abstract class HackerThread extends Thread{
        protected Vault vault;

        public HackerThread(Vault vault){
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start(){
            System.out.println("Starting thread" + this.getName());
            super.start();
        }
    }
    private static class AscendingHackerThread extends HackerThread {

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run(){
            for(int i = 0; i < MAX_PASSWORD; i++){
                if(vault.isCorrectPassword(i)){
                    System.out.println(this.getName() + " guessed the password: " + i);
                    System.exit(0);
                }
            }
        }
    }
    private static class DescendingHackerThread extends HackerThread{

        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run(){
            for(int i = MAX_PASSWORD; i > 0; i-- ){
                if(vault.isCorrectPassword(i)){
                    System.out.println(getName() + " guessed the password: " + i);
                    System.exit(0);
                }
            }
        }
    }
    public static class PoliceClass extends Thread{

        @Override
        public void run(){
            for(int i = 0; i<10;i++){
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException ex){
                }
            }
            System.out.println("You are caught, hackers!!!");
            System.exit(0);
        }
    }
}
