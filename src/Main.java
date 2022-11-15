import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws SocketException, UnknownHostException {


            switch (args[0]){
                case "cli":
                    Cliente cli = new Cliente(Integer.valueOf(args[2]),args[1]);
                    cli.start();
                    break;
                case "ser":
                    Servidor ser = new Servidor(Integer.valueOf(args[2]));
                    ser.start();
                    break;
                default:
                    System.out.println("error de sintaxis ser/cli ip puerto");
                    break;
            }

    }
}
