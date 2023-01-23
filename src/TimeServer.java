import java.net.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class TimeServer {
    public static void main(String[] args) throws Exception {
        String req = "";
        DatagramSocket socket = new DatagramSocket(8888);
        DatagramPacket request = null;
        while (true) {
            byte[] buffer = new byte[1024];
            request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);
            req = new String(request.getData());
            System.out.println("[CLIENT]: " + req);
            long diff = ChronoUnit.SECONDS.between(LocalDateTime.of(1970, 1, 1, 0, 0, 0), LocalDateTime.now());
            buffer = Long.toString(diff).getBytes();
            request = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 8889);
            socket.send(request);
        }

    }
}
