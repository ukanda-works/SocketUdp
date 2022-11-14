import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente extends Thread {

    private int puerto_server;
    private InetAddress ip_server;
    private DatagramSocket socket;

    public Cliente(int puerto_server, String server_host) throws UnknownHostException, SocketException {
        this.puerto_server = puerto_server;
        this.ip_server = InetAddress.getByName(server_host);
        this.socket = new DatagramSocket();
    }

    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (true){
            String mensaje = mensaje();
            comunicar(mensaje);

        }
    }

    private String mensaje(){
        System.out.print("Cliente: Introduce el mensaje a enviar: ");
        Scanner scanner = new Scanner(System.in);
        String mensaje = scanner.nextLine();

        return mensaje;
    }

    private void comunicar(String mensaje){

       byte[] buffer = mensaje.getBytes();
        try {
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket paqueteEnviado = new DatagramPacket(buffer, buffer.length, ip_server, puerto_server);
            socket.send(paqueteEnviado);
            sleep(2000);

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
