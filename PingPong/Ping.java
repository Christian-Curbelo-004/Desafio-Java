package PingPong;

import java.util.concurrent.Semaphore;

public class Ping extends Thread {
    public int turnoUno = 1;
    public int turnoDos = 2;


    Semaphore turnoUnoSemaphore = new Semaphore(1);
    Semaphore turnoDosSemaphore = new Semaphore(0);

    public Ping(int turnoUno, int turnoDos) { 
        this.turnoUno = turnoUno;
        this.turnoDos = turnoDos;
    }
    
    public void run(){
        try{
            for(int i = 1; i <= 10; i++){
                turnoUnoSemaphore.acquire();
                System.out.println("Ping");
                turnoDosSemaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public class Pong extends Thread {
        public void run(){
            try{
                for(int i = 1; i <= 10; i++){
                    turnoDosSemaphore.acquire();
                    System.out.println("Pong");
                    turnoUnoSemaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Ping ping = new Ping(1, 2);
        Pong pong = ping.new Pong();

        ping.start();
        pong.start();
    }
}