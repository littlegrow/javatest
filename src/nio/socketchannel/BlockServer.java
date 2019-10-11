package nio.socketchannel;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class BlockServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            while (true) {
                dealConnect(serverSocket.accept());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void dealConnect(Socket socket) {
        InputStream inputStream = null;
        try {
            int receiveSize;
            byte[] receiveBuffer = new byte[1024];
            SocketAddress clientAddress = socket.getRemoteSocketAddress();
            System.out.println("Receive socket connect at " + clientAddress);

            inputStream = socket.getInputStream();

            while ((receiveSize = inputStream.read(receiveBuffer)) != -1) {
                byte[] tmp = new byte[receiveSize];
                System.arraycopy(receiveBuffer, 0, tmp, 0, receiveSize);
                System.out.println(new String(tmp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
