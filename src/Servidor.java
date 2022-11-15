import java.io.IOException;
import java.net.*;
import java.time.LocalDateTime;

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

    public static void main(String[] args) throws SocketException {
        Servidor servidor = new Servidor(Integer.valueOf(args[0]));
        servidor.start();
    }
    /**
     * Lanza el servidor y lo deja a la espera de recibir un paquete
     */
    public void launchServer(){

        try {
            DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length);//declaramos el paquete que vamos a recibir
            aLaEscucha(datagrama);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void aLaEscucha(DatagramPacket datagrama) throws IOException {
        System.out.println("Servidor: a la espera y listo para recibir en el puerto "+socket.getLocalPort());
        socket.receive(datagrama);//se queda a la espera de recibir un paquete
        procesar(datagrama);
    }

    public void procesar(DatagramPacket datagrama) throws IOException {
        System.out.println("Servidor: mensaje recivido: ");
        String mensaje = new String(datagrama.getData());//se recoge la data del paquete udp
        System.out.println(mensaje.replace("\u0000",""));//mostramos el contenido por pantalla
        devolverDayTIme(datagrama);

    }

    public void devolverDayTIme(DatagramPacket datagrama) throws IOException {
        int puertoCli = datagrama.getPort();//cogemos el puerto que ha usado el cliente para mandar el paquete
        InetAddress ipCli = datagrama.getAddress();//cogemos la ip que ha usado para enviar el paquete
        System.out.println("Servidor: Enviando hora a "+ipCli+":"+puertoCli);
        byte[] buffer = LocalDateTime.now().toString().getBytes();
        socket.send(new DatagramPacket(buffer, buffer.length,ipCli,puertoCli));
        try {sleep(500);}
        catch (InterruptedException e) {throw new RuntimeException(e);}
        aLaEscucha(datagrama);
    }




}
