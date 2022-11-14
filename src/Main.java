import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws SocketException, UnknownHostException {
            //en este caso el servidor escuchará en el 5000
            Servidor ser = new Servidor(5000);
            Cliente cli = new Cliente(5000,"localhost");

            //iniciamos los dos hilos
            ser.start();
            cli.start();

    }
}
