package nio.socketchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NioServer {
    private static final int BUFFER_SIZE = 1024;
    private static final int PORT = 8080;
    private static final int TIMEOUT = 300000;

    public static void main(String[] args) {
        selector();
    }

    private static void selector() {
        Selector selector = null;
        ServerSocketChannel serverSocketChannel = null;

        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            for (; ; ) {
                if (selector.select(TIMEOUT) == 0) {
                    System.out.println("==");
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                try {
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (!selectionKey.isValid()) {
                            System.out.println(":invalid");
                            iterator.remove();
                            continue;
                        }
                        if (selectionKey.isAcceptable()) {
                            System.out.println(":accept");
                            handleAccept(selectionKey);
                        }
                        if (selectionKey.isReadable()) {
                            System.out.println(":read");
                            handleRead(selectionKey);
                        }
                        if (selectionKey.isWritable()) {
                            System.out.println(":write");
                            handleWrite(selectionKey);
                        }
                        if (selectionKey.isConnectable()) {
                            System.out.println("is connecting");
                        }
                        iterator.remove();
                    }
                } catch (CancelledKeyException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (selector != null) {
                    selector.close();
                }
                if (serverSocketChannel != null) {
                    serverSocketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void handleAccept(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("Accept: " + socketChannel.getRemoteAddress());
        socketChannel.configureBlocking(false);
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUFFER_SIZE));
    }

    private static void handleRead(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
        while (socketChannel.read(buffer) > 0) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            System.out.println();
            buffer.clear();
        }
    }

    private static void handleWrite(SelectionKey selectionKey) throws IOException {
        ByteBuffer buf = (ByteBuffer) selectionKey.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) selectionKey.channel();
        while (buf.hasRemaining()) {
            sc.write(buf);
        }
        buf.compact();
    }
}
