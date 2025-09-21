package CuentaBancaria;

import java.util.concurrent.Semaphore;

public class CuentaBancaria extends Thread {
    public String clienteUno;
    public String clienteDos;
    public String clienteTres;
    public int saldo;
    public int retiro;
    public int deposito;

    Semaphore turnoUnoSemaphore = new Semaphore(1);
    Semaphore turnoDosSemaphore = new Semaphore(0);

    public CuentaBancaria(String clienteUno, String clienteDos, String clienteTres, int saldo, int retiro, int deposito){
        this.clienteUno = clienteUno;
        this.clienteDos = clienteDos;
        this.clienteTres = clienteTres;
        this.saldo = saldo;
        this.retiro = retiro;
        this.deposito = deposito;
    }
    public void run(){
        try{
            for(int i = 1; i <= 5; i++){
                turnoUnoSemaphore.acquire();
                if(saldo >= retiro){
                    saldo = saldo - retiro;
                    System.out.println(clienteUno + " ha retirado: " + retiro + " y su saldo es: " + saldo);
                } else {
                    System.out.println(clienteUno + " no tiene saldo suficiente para retirar: " + retiro + " y su saldo es: " + saldo);
                }
                turnoDosSemaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class ClienteDos extends Thread {
        public void run(){
            try{
                for(int i = 1; i <= 5; i++){
                    turnoDosSemaphore.acquire();
                    saldo = saldo + deposito;
                    System.out.println(clienteDos + " ha depositado: " + deposito + " y su saldo es: " + saldo);
                    turnoUnoSemaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class ClienteTres extends Thread {
        public void run(){
            try{
                for(int i = 1; i <= 5; i++){
                    turnoDosSemaphore.acquire();
                    if(saldo >= retiro){
                        saldo = saldo - retiro;
                        System.out.println(clienteTres + " ha retirado: " + retiro + " y su saldo es: " + saldo);
                    } else {
                        System.out.println(clienteTres + " no tiene saldo suficiente para retirar: " + retiro + " y su saldo es: " + saldo);
                    }
                    turnoUnoSemaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria("Cliente 1", "Cliente 2", "Cliente 3", 1000, 200, 150);
        ClienteDos cliente2 = cuenta.new ClienteDos();
        ClienteTres cliente3 = cuenta.new ClienteTres();

        cuenta.start();
        cliente2.start();
        cliente3.start();   
    }
}
