package CarreraDeAutos;

import java.util.Random;


public class CarreraDeAutos extends Thread {
    public String nombre;
    public int distancia;
    public static final int meta = 100;
    public int posicion = 0;
    public static boolean ganador = false;
    private final Random random = new Random();

    public CarreraDeAutos(final String nombre) {
        this.nombre = nombre;
    }
    
    public void run() {
        while(posicion <= meta && !ganador ){
            final int paso = random.nextInt (10) + 1;
            posicion += paso;
             System.out.println("[Corredor " + nombre + "] avanza " + paso + " --> total: " + posicion);

             if(posicion >= meta && !ganador){
                 System.out.println("Gano el corredor:  " + nombre);
             }
             try{
                Thread.sleep(500);
             }catch (InterruptedException e){
                e.printStackTrace();
             
             }
        }
    }

    public static void main(final String [] args) {
        final CarreraDeAutos A1 = new CarreraDeAutos("A1: ");
        final CarreraDeAutos A2 = new CarreraDeAutos("A2: ");
        final CarreraDeAutos A3 = new CarreraDeAutos("A3: ");

        final Thread t1 = new Thread(A1);
        final Thread t2 = new Thread(A2);
        final Thread t3 = new Thread(A3);

        t1.start();
        t2.start();
        t3.start();
    }

}