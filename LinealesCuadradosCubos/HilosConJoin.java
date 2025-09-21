package LinealesCuadradosCubos;


public class HilosConJoin extends Thread {
    public String nombre;
    
    
    public HilosConJoin(String nombre, int numero){
        this.nombre = nombre;
    }
    public void run(){
        for(int n = 1; n <= 5; n++){
            if(nombre == "Cuadrado"){
                System.out.println("Hilo A : " + nombre + " son: " + (n * n ));
            } else if(nombre == "Cubo"){
                System.out.println("Hilo B: " + nombre + " son: " + (n * n * n ));
            } else if (nombre =="Del 1 al 5 "){
                System.out.println("Hilo C: " + nombre + "son: " + ("Numero " + n));
            }
        }

    }

    public static void main(String[] args) {
        HilosConJoin h1 = new HilosConJoin("Cuadrado", 1);
        HilosConJoin h2 = new HilosConJoin("Cubo", 2);
        HilosConJoin h3 = new HilosConJoin("Del 1 al 5 ", 3);

        h1.start();
        try {
            h1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        h2.start();
        try {
            h2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        h3.start();
        try {
            h3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
    

   