import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws SocketException, UnknownHostException {

            Servidor ser = new Servidor(5000);
            Cliente cli = new Cliente(5000,"localhost");

            ser.start();

            cli.start();

    }
}
