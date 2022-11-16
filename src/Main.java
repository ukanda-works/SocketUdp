import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws SocketException, UnknownHostException {


            switch (args[0]){
                case "cliente":
                    Cliente cli = new Cliente(Integer.valueOf(args[1]),args[2]);
                    cli.start();
                    break;
                case "servidor":
                    Servidor ser = new Servidor(Integer.valueOf(args[1]));
                    ser.start();
                    break;
                default:
                    System.out.println("error de sintaxis ser/cli ip puerto");
                    break;
            }

    }
}
