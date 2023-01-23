import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        DatagramSocket socket = new DatagramSocket(8889);
        while (true) {
            System.out.print("Inserisci un input: ");
            input = read.readLine();
            byte[] buffer = input.getBytes();
            DatagramPacket pack = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 8888);
            socket.send(pack);
            buffer = new byte[1024];
            pack = new DatagramPacket(buffer, buffer.length);
            socket.setSoTimeout(1000);
            try {
                socket.receive(pack);
                System.out.println("[SERVER]: Secondi passati dal 01/01/1970 = " + new String(pack.getData()));
            } catch (SocketTimeoutException e) {
                System.out.println("TIMEOUT");
            }
        }

    }
}
