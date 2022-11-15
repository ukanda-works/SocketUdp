import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente extends Thread {

    private int puerto_server;//puerto que trabajara el servidor
    private InetAddress ip_server;//ip del server
    private DatagramSocket socket;//socket que se usa para la comunicacion

    /**
     * Constructor
     * @param puerto_server//puerto en el que se va a trabajar
     * @param server_host //nombre del host que hara de servidor
     * @throws UnknownHostException //en caso de la ip no se reconozca
     * @throws SocketException  //en caso de que no se inicie el socket adecuadamente
     */
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

    public static void main(String[] args) throws SocketException, UnknownHostException {
        Cliente cliente = new Cliente(Integer.valueOf(args[0]), args[1]);
        cliente.start();
    }

    /**
     * pregunta por consola el mensaje que se va a enviar
     * @return el mensaje que va a enviar el usuario
     */
    private String mensaje(){
        System.out.print("Cliente: Introduce el mensaje a enviar: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Envia un paquete udp al servidor
     * @param mensaje mensaje a enviar
     */
    private void comunicar(String mensaje){
       byte[] buffer = mensaje.getBytes();//stablecemos el buffer que enviaremos
        try {
            DatagramPacket paqueteEnviado = new DatagramPacket(buffer, buffer.length, ip_server, puerto_server); // se crea el paquete a enviar usando el buffer
            socket.send(paqueteEnviado);//se envia por medio del socket
            sleep(2000);//se detiene 2 segundos

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
