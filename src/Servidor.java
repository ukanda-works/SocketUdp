import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor extends Thread{
    private int puerto;
    private byte[] buffer = new byte[1024];
    private DatagramSocket socket;

    public Servidor(int puerto) throws SocketException {
        this.puerto = puerto;
        socket = new DatagramSocket(puerto);
    }

    @Override
    public void run() {
        launchServer();
    }

    public void launchServer(){
        System.out.println("Servidor: a la espera y listo para recibir:");
        while (true){
        try {
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            socket.receive(peticion);


            System.out.println("Servidor: mensaje recivido: ");
            String mensaje = new String(peticion.getData());
            System.out.println(mensaje.replace("\u0000",""));

            int puertoCli = peticion.getPort();
            InetAddress ipCli = peticion.getAddress();
            System.out.println("Servidor: El cliente se ha comunicado por la direccion "+ipCli+":"+puertoCli);

            //Esto es para la respuesta del server
            /*String mensajeRespuesta = "Respuesta del servidor";
            buffer = mensajeRespuesta.getBytes();
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);

            socket.send(respuesta);*/


        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }




}
