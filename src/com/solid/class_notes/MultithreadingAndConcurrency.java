package com.solid.class_notes;

public class MultithreadingAndConcurrency {

    /*
    *
    * Multi-threading:
    * Implementing multi-threading is in 2 ways:
    * 1. Extending the Thread class.
    * 2. Implementing the Runnable interface.
    *
    * Synchronization and Locks (Live lock, dead-lock, white lock)
    * Thread Safety and Race conditions
    *
    * */

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyThread());
        Thread thread2 = new Thread(new MyThread());

        try{
            thread1.start();
            thread1.suspend();
            thread1.resume();
            thread1.join();
            thread2.start();
        }catch (InterruptedException ex){
            System.out.println("Something interrupted our thread");
        }
    }
}

class MyThread implements Runnable{

    @Override
    public void run() {
        for(int i = 1; i < 10; i++){
            System.out.println("Thread: " + Thread.currentThread().getId() + "\tValue: " + i) ;
        }
    }
}
