import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor extends Thread{
    private int puerto;
    private byte[] buffer = new byte[1024];//buffer que usaremos por default
    private DatagramSocket socket;//socket con el que se realizara la conexion

    /**
     * Constructor
     * @param puerto puerto en el que trabajara el servidor
     * @throws SocketException en caso de que falle la creacion del socket
     */
    public Servidor(int puerto) throws SocketException {
        this.puerto = puerto;
        socket = new DatagramSocket(puerto);
    }

    @Override
    public void run() {
        launchServer();
    }

    /**
     * Lanza el servidor y lo deja a la espera de recibir un paquete
     */
    public void launchServer(){
        System.out.println("Servidor: a la espera y listo para recibir:");
        while (true){
        try {
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length); //declaramos el paquete que vamos a recibir
            socket.receive(peticion);//se queda a la espera de recibir un paquete


            System.out.println("Servidor: mensaje recivido: ");
            String mensaje = new String(peticion.getData());//se recoge la data del paquete udp
            System.out.println(mensaje.replace("\u0000",""));//mostramos el contenido por pantalla

            int puertoCli = peticion.getPort();//cogemos el puerto que ha usado el cliente para mandar el paquete
            InetAddress ipCli = peticion.getAddress();//cogemos la ip que ha usado para enviar el paquete
            System.out.println("Servidor: El cliente se ha comunicado por la direccion "+ipCli+":"+puertoCli);

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }




}
