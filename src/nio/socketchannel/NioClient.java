package nio.socketchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class NioClient {

    private static void client() {
        Random random = new Random(System.currentTimeMillis());
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
            if (socketChannel.finishConnect()) {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                String info = Thread.currentThread().getName() + "information";
                buffer.clear();
                buffer.put(info.getBytes());
                buffer.flip();
                socketChannel.write(buffer);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null) {
                    System.out.println("close socket");
                    socketChannel.shutdownInput();
                    socketChannel.shutdownOutput();
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(0);
        ExecutorService executorService = Executors.newCachedThreadPool(r -> new Thread(r, "client-" + integer.addAndGet(1)));
        for (int i = 0; i < 10; i++) {
            executorService.submit(NioClient::client);
        }
    }
}
