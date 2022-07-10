package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStream {
    ServerStream() {
        System.out.println("Server started!");
        start();
    }

    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(ServerConfig.getPort(), 50,
                InetAddress.getByName(ServerConfig.getAddress()))) {
            try (Socket socket = serverSocket.accept();
                 DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                 DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {
                String msg = inputStream.readUTF();
                System.out.println("Received: " + msg);
                String reply = "All files were sent!";
                System.out.println("Sent: " + reply);
                outputStream.writeUTF(reply);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
